package com.WB.API.mapper;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.WB.API.assertions.LanguageAssertions;
import com.WB.API.assertions.TestDatas;
import com.WB.API.dto.LanguageDTO;
import com.WB.API.model.Language;

@ActiveProfiles("test")
@DataJpaTest
@DisplayName("Test du mapper des langues")
public class LanguageMapperTests {

	@Test
	@DisplayName("Test de conversion d'une entité langue en objet de transfert")
	public void testEntityToDTO_ReturnCompleteDTO() {
		// Arrange
		Language entity = LanguageAssertions.getLanguage();

		// Act
		LanguageDTO dto = LanguageMapper.toDTO(entity);

		// Assert
		LanguageAssertions.assertEqualsProperties(entity, dto);
	}

	@Test
	@DisplayName("Test de conversion d'une entité langue null en objet de transfert")
	public void testEntityToDTO_ReturnNullDTO() {
		// Arrange set to Null + Act
		LanguageDTO dto = LanguageMapper.toDTO(null);

		// Assert
		Assertions.assertNull(dto);
	}

	@Test
	@DisplayName("Test de conversion d'une liste d'entités langue en liste d'objets de transfert")
	public void testEntityListToDTOList_ReturnDTOList() {
		// Arrange
		TestDatas<Language, LanguageDTO> datas = LanguageAssertions.getLanguageTestDatas(3);

		// Act
		List<LanguageDTO> listDTO = LanguageMapper.toDTOList(datas.entities);

		// Assert
		LanguageAssertions.assertListEntitiesDTOs(datas.entities, listDTO);
	}

	@Test
	@DisplayName("Test de conversion d'une liste d'entités langue null en liste d'objets de transfert")
	public void testEntityListToDTOList_ReturnNullDTOList() {
		// Arrange set to Null + Act + Assert
		Assertions.assertNull(LanguageMapper.toDTOList(null));
	}

	@Test
	@DisplayName("Test de conversion d'un objet de transfert langue en entité")
	public void testDTOToEntity_ReturnCompleteEntity() {
		// Arrange
		LanguageDTO dto = LanguageAssertions.getLanguageDTO();

		// Act
		Language entity = LanguageMapper.toEntity(dto);

		// Assert
		LanguageAssertions.assertEqualsProperties(entity, dto);
	}

	@Test
	@DisplayName("Test de conversion d'un objet de transfert langue null en entité")
	public void testDTOToEntity_ReturnNullEntity() {
		// Arrange set to Null + Act
		Language entity = LanguageMapper.toEntity(null);

		// Assert
		Assertions.assertNull(entity);
	}

	@Test
	@DisplayName("Test de conversion d'une liste d'objets de transfert langue en liste d'entités")
	public void testDTOListToEntityList_ReturnCompleteEntityList() {
		// Arrange
		TestDatas<Language, LanguageDTO> datas = LanguageAssertions.getLanguageTestDatas(3);

		// Act
		List<Language> listEntities = LanguageMapper.toEntityList(datas.dtos);

		// Assert
		LanguageAssertions.assertListEntitiesDTOs(listEntities, datas.dtos);
	}

	@Test
	@DisplayName("Test de conversion d'une liste d'objets de transfert langue null en liste d'entités")
	public void testDTOListToEntityList_ReturnNullEntityList() {
		// Arrange set to Null + Act + Assert
		Assertions.assertNull(LanguageMapper.toEntityList(null));
	}
}

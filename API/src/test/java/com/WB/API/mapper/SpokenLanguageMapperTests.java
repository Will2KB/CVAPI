package com.WB.API.mapper;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.WB.API.assertions.SpokenLanguageAssertions;
import com.WB.API.assertions.TestDatas;
import com.WB.API.dto.SpokenLanguageDTO;
import com.WB.API.model.SpokenLanguage;

@ActiveProfiles("test")
@DataJpaTest
@DisplayName("Test du mapper des langues parlées")
public class SpokenLanguageMapperTests {

	@Test
	@DisplayName("Test de conversion d'une entité langue parlé en objet de transfert")
	public void testEntityToDTO_ReturnCompleteDTO() {
		// Arrange
		SpokenLanguage entity = SpokenLanguageAssertions.getSpokenLanguage();

		// Act
		SpokenLanguageDTO dto = SpokenLanguageMapper.toDTO(entity);

		// Assert
		SpokenLanguageAssertions.assertNotNullDTO(dto);
		SpokenLanguageAssertions.assertEqualsProperties(entity, dto);
	}

	@Test
	@DisplayName("Test de conversion d'une entité langue parlé null en objet de transfert")
	public void testEntityToDTO_ReturnNullDTO() {
		// Arrange set to null + Act
		SpokenLanguageDTO dto = SpokenLanguageMapper.toDTO(null);

		// Assert
		Assertions.assertNull(dto);
	}

	@Test
	@DisplayName("Test de conversion d'une liste d'entités langue parlé en liste d'objets de transfert")
	public void testEntityListToDTOList_ReturnCompleteListDTO() {
		// Arrange
		TestDatas<SpokenLanguage, SpokenLanguageDTO> datas = SpokenLanguageAssertions.getSpokenLanguageTestDatas(6);

		// Act
		List<SpokenLanguageDTO> listDTO = SpokenLanguageMapper.toDTOList(datas.entities);

		// Assert
		SpokenLanguageAssertions.assertListEntitiesDTOs(datas.entities, listDTO);
	}

	@Test
	@DisplayName("Test de conversion d'une liste d'entités langue parlé null en liste d'objets de transfert")
	public void testEntityListToDTOList_ReturnNullListDTO() {
		// Arrange set to null + Act + Assert
		Assertions.assertNull(SpokenLanguageMapper.toDTOList(null));
	}

	@Test
	@DisplayName("Test de conversion d'un objet de transfert langue parlé en entité")
	public void testDtoToEntity_ReturnCompleteEntity() {
		// Arrange
		SpokenLanguageDTO dto = SpokenLanguageAssertions.getSpokenLanguageDTO();

		// Act
		SpokenLanguage entity = SpokenLanguageMapper.toEntity(dto);

		// Assert
		SpokenLanguageAssertions.assertNotNullEntity(entity);
		SpokenLanguageAssertions.assertEqualsProperties(entity, dto);
	}

	@Test
	@DisplayName("Test de conversion d'un objet de transfert langue parlé null en entité")
	public void testDtoToEntity_ReturnNullEntity() {
		// Arrange set to null + Act
		SpokenLanguage entity = SpokenLanguageMapper.toEntity(null);

		// Assert
		Assertions.assertNull(entity);
	}

	@Test
	@DisplayName("Test de conversion d'une liste d'objets de transfert langue parlé en une liste d'entités")
	public void testDTOListToEntityList_ReturnCompleteEntityList() {
		// Arrange
		TestDatas<SpokenLanguage, SpokenLanguageDTO> datas = SpokenLanguageAssertions.getSpokenLanguageTestDatas(6);

		// Act
		List<SpokenLanguage> listEntities = SpokenLanguageMapper.toEntityList(datas.dtos);

		// Assert
		SpokenLanguageAssertions.assertListEntitiesDTOs(listEntities, datas.dtos);
	}

	@Test
	@DisplayName("Test de conversion d'une liste d'objets de transfert langue parlé null en une liste d'entités")
	public void testDTOListToEntityList_ReturnNullEntityList() {
		// Arrange set to Null + Act + Assert
		Assertions.assertNull(SpokenLanguageMapper.toEntityList(null));
	}
}

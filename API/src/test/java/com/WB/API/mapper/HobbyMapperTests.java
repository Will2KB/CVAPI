package com.WB.API.mapper;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.WB.API.assertions.HobbyAssertions;
import com.WB.API.assertions.TestDatas;
import com.WB.API.dto.HobbyDTO;
import com.WB.API.model.Hobby;

@ActiveProfiles("test")
@DataJpaTest
@DisplayName("Test du mapper des passions")
public class HobbyMapperTests {

	@Test
	@DisplayName("Test de conversion d'une entité passion en objet de transfert")
	public void testEntityToDTO_ReturnCompleteDTO() {
		// Arrange
		Hobby entity = HobbyAssertions.getHobby();

		// Act
		HobbyDTO dto = HobbyMapper.toDTO(entity);

		// Assert
		HobbyAssertions.assertEqualsProperties(entity, dto);
	}

	@Test
	@DisplayName("Test de conversion d'une entité passion null en objet de transfert")
	public void testEntityToDTO_ReturnNullDTO() {
		// Arrange set to Null + Act
		HobbyDTO dto = HobbyMapper.toDTO(null);

		// Assert
		Assertions.assertNull(dto);
	}

	@Test
	@DisplayName("Test de conversion d'une liste d'entités passion en liste d'objets de transfert")
	public void testEntityListToDTOList_ReturnCompleteDTOList() {
		// Arrange
		TestDatas<Hobby, HobbyDTO> datas = HobbyAssertions.getHobbyTestDatas(6);

		// Act
		List<HobbyDTO> listDTO = HobbyMapper.toDTOList(datas.entities);

		// Assert
		HobbyAssertions.assertListEntitiesDTOs(datas.entities, listDTO);
	}

	@Test
	@DisplayName("Test de conversion d'une liste d'entités passion null en liste d'objets de transfert")
	public void testEntityListToDTOList_RerturnNullDTOList() {
		// Arrange set to Null + Act + Assert
		Assertions.assertNull(HobbyMapper.toDTOList(null));
	}

	@Test
	@DisplayName("Test de conversion d'un objet de transfert passion en entité")
	public void testDTOToEntity_ReturnCompleteEntity() {
		// Arrange
		HobbyDTO dto = HobbyAssertions.getHobbyDTO();

		// Act
		Hobby entity = HobbyMapper.toEntity(dto);

		// Assert
		HobbyAssertions.assertNotNullEntity(entity);
		HobbyAssertions.assertEqualsProperties(entity, dto);
	}

	@Test
	@DisplayName("Test de conversion d'un objet de transfert passion null en entité")
	public void testDTOToEntity_ReturnNullEntity() {
		// Arrange set to Null + Act
		Hobby entity = HobbyMapper.toEntity(null);

		// Assert
		Assertions.assertNull(entity);
	}

	@Test
	@DisplayName("Test de conversion d'une liste d'objets de transfert passion en liste d'entités")
	public void testDTOListToEntityList_ReturnCompleteEntityList() {
		// Arrange
		TestDatas<Hobby, HobbyDTO> datas = HobbyAssertions.getHobbyTestDatas(6);

		// Act
		List<Hobby> listEntities = HobbyMapper.toEntityList(datas.dtos);

		// Assert
		HobbyAssertions.assertListEntitiesDTOs(listEntities, datas.dtos);
	}

	@Test
	@DisplayName("Test de conversion d'une liste d'objets de transfert passion null en liste d'entités")
	public void testDTOListToEntityList_ReturnNullEntityList() {
		// Arrange set to Null + Act + Assert
		Assertions.assertNull(HobbyMapper.toEntityList(null));
	}
}

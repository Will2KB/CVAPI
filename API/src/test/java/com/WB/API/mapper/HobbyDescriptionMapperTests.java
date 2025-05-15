package com.WB.API.mapper;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.WB.API.assertions.HobbyDescriptionAssertions;
import com.WB.API.assertions.TestDatas;
import com.WB.API.dto.HobbyDescriptionDTO;
import com.WB.API.model.HobbyDescription;

@ActiveProfiles("test")
@DataJpaTest
@DisplayName("Test du mapper des descriptions de passion")
public class HobbyDescriptionMapperTests {

	@Test
	@DisplayName("Test de conversion d'une entité description de passion en objet de transfert")
	public void testEntityToDTO_ReturnCompleteDTO() {
		// Arrange
		HobbyDescription entity = HobbyDescriptionAssertions.getHobby();

		// Act
		HobbyDescriptionDTO dto = HobbyDescriptionMapper.toDTO(entity);

		// Assert
		HobbyDescriptionAssertions.assertEqualsProperties(entity, dto);
	}

	@Test
	@DisplayName("Test de conversion d'une entité description de passion null en objet de transfert")
	public void testEntityToDTO_ReturnNullDTO() {
		// Arrange set to Null + Act
		HobbyDescriptionDTO dto = HobbyDescriptionMapper.toDTO(null);

		// Assert
		Assertions.assertNull(dto);
	}

	@Test
	@DisplayName("Test de conversion d'une liste d'entités description de passion en liste d'objets de transfert")
	public void testEntityListToDTOList_ReturnDTOList() {
		// Arrange
		TestDatas<HobbyDescription, HobbyDescriptionDTO> datas = HobbyDescriptionAssertions
				.getHobbyDescriptionTestDatas(5);

		// Act
		List<HobbyDescriptionDTO> listDTO = HobbyDescriptionMapper.toDTOList(datas.entities);

		// Assert
		HobbyDescriptionAssertions.assertListEntitiesDTOs(datas.entities, listDTO);
	}

	@Test
	@DisplayName("Test de conversion d'une liste d'entités description de passion null en liste d'objets de transfert")
	public void testEntityListToDTOList_ReturnNullDTOList() {
		// Arrange set to Null + Act +Assert
		Assertions.assertNull(HobbyDescriptionMapper.toDTOList(null));
	}

	@Test
	@DisplayName("Test de conversion d'un objet de transfert description de passion en entité")
	public void testDTOToEntity_ReturnCompleteEntity() {
		// Arrange
		HobbyDescriptionDTO dto = HobbyDescriptionAssertions.getHobbyDescriptionDTO();

		// Act
		HobbyDescription entity = HobbyDescriptionMapper.toEntity(dto);

		// Assert
		HobbyDescriptionAssertions.assertEqualsProperties(entity, dto);
	}

	@Test
	@DisplayName("Test de conversion d'un objet de transfert description de passion null en entité")
	public void testDTOToEntity_ReturnNullEntity() {
		// Arrange set to Null + Act
		HobbyDescription entity = HobbyDescriptionMapper.toEntity(null);

		// Assert
		Assertions.assertNull(entity);
	}

	@Test
	@DisplayName("Test de conversion d'une liste d'objets de transfert description de passion en liste d'entités")
	public void testDTOListToEntityList_ReturnCompleteEntityList() {
		// Arrange
		TestDatas<HobbyDescription, HobbyDescriptionDTO> datas = HobbyDescriptionAssertions
				.getHobbyDescriptionTestDatas(5);

		// Act
		List<HobbyDescription> listEntities = HobbyDescriptionMapper.toEntityList(datas.dtos);

		// Assert
		HobbyDescriptionAssertions.assertListEntitiesDTOs(listEntities, datas.dtos);
	}

	@Test
	@DisplayName("Test de conversion d'une liste d'objets de transfert description de passion null en liste d'entités")
	public void testDTOListToEntityList_ReturnNullEntityList() {
		// Arrange set to Null + Act + Assert
		Assertions.assertNull(HobbyDescriptionMapper.toEntityList(null));
	}
}

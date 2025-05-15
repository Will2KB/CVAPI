package com.WB.API.mapper;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.WB.API.assertions.SkillTypeAssertions;
import com.WB.API.assertions.TestDatas;
import com.WB.API.dto.SkillTypeDTO;
import com.WB.API.model.SkillType;

@ActiveProfiles("test")
@DataJpaTest
@DisplayName("Test du mapper des types de compétence")
public class SkillTypeMapperTests {

	@Test
	@DisplayName("Test de conversion d'une entité type de compétence en objet de transfert")
	public void testEntityToDTO_ReturnCompleteDTO() {
		// Arrange
		SkillType skillTypeEntity = SkillTypeAssertions.getSkillType();

		// Act
		SkillTypeDTO skillTypeDTO = SkillTypeMapper.toDTO(skillTypeEntity);

		// Assert
		SkillTypeAssertions.assertEqualsProperties(skillTypeEntity, skillTypeDTO);
	}

	@Test
	@DisplayName("Test de conversion d'une entité type de compétence null en objet de transfert")
	public void testEntityToDTO_ReturnNullDTO() {
		// Arrange set to null + Act
		SkillTypeDTO skillTypeDTO = SkillTypeMapper.toDTO(null);

		// Assert
		Assertions.assertNull(skillTypeDTO);
	}

	@Test
	@DisplayName("Test de conversion d'une liste d'entités type de compétence en liste d'objets de transfert")
	public void testEntityListToDTOList_ReturnCompleteDTOList() {
		// Arrange
		TestDatas<SkillType, SkillTypeDTO> datas = SkillTypeAssertions.getSkillTypeTestDatas(6);

		// Act
		List<SkillTypeDTO> skillTypesDTOMapped = SkillTypeMapper.toDTOList(datas.entities);

		// Assert
		SkillTypeAssertions.assertListEntitiesDTOs(datas.entities, skillTypesDTOMapped);

	}

	@Test
	@DisplayName("Test de conversion d'une liste d'entités type de compétence null en liste d'objets de transfert")
	public void testEntityListToDTOList_ReturnNullDTOList() {
		// Arrange set to null + Act
		List<SkillTypeDTO> skillTypesDTOMapped = SkillTypeMapper.toDTOList(null);

		// Assert
		Assertions.assertNull(skillTypesDTOMapped);
	}

	@Test
	@DisplayName("Test de conversion d'un objet de transfert type de compétence en entité")
	public void testDTOToEntity_ReturnCompleteEntity() {
		// Arrange
		SkillTypeDTO skillTypeDTO = SkillTypeAssertions.getSkillTypeDTO();

		// Act
		SkillType skillTypeEntity = SkillTypeMapper.toEntity(skillTypeDTO);

		// Assert
		SkillTypeAssertions.assertEqualsProperties(skillTypeEntity, skillTypeDTO);

	}

	@Test
	@DisplayName("Test de conversion d'un objet de transfert type de compétence null en entité")
	public void testDTOToEntity_ReturnNullEntity() {
		// Arrange set to null + Act
		SkillType skillTypeEntity = SkillTypeMapper.toEntity(null);

		// Assert
		Assertions.assertNull(skillTypeEntity);
	}

	@Test
	@DisplayName("Test de conversion d'une liste d'objets de transfert type de compétence en liste d'entité")
	public void testDTOListToEntityList_ReturnCompleteEntityList() {
		// Arrange
		TestDatas<SkillType, SkillTypeDTO> datas = SkillTypeAssertions.getSkillTypeTestDatas(6);

		// Act
		List<SkillType> skillTypesEntityMapped = SkillTypeMapper.toEntityList(datas.dtos);

		// Assert
		SkillTypeAssertions.assertListEntitiesDTOs(skillTypesEntityMapped, datas.dtos);
	}

	@Test
	@DisplayName("Test de conversion d'une liste d'objets de transfert type de compétence null en liste d'entité")
	public void testDTOListToEntityList_ReturnNullEntityList() {
		// Arrange set to null + Act
		List<SkillType> skillTypesEntityMapped = SkillTypeMapper.toEntityList(null);

		// Assert
		Assertions.assertNull(skillTypesEntityMapped);
	}

}

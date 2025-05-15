package com.WB.API.mapper;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.WB.API.assertions.SkillAssertions;
import com.WB.API.assertions.TestDatas;
import com.WB.API.dto.SkillDTO;
import com.WB.API.model.Skill;

@ActiveProfiles("test")
@DataJpaTest
@DisplayName("Test du mapper des compétences")
public class SkillMapperTests {

	@Test
	@DisplayName("Test de conversion d'une entité compétence sans type en objet de transfert")
	public void testEntityToDTO_ReturnDTOWithoutType() {
		// Arrange
		Skill entity = SkillAssertions.getSkill();

		// Act
		SkillDTO dto = SkillMapper.toDTO(entity);

		// Assert
		SkillAssertions.assertEqualsProperties(entity, dto);

	}

	@Test
	@DisplayName("Test de conversion d'une entité compétence en objet de transfert")
	public void testEntityToDTO_ReturnCompleteDTO() {
		// Arrange
		Skill entity = SkillAssertions.getSkillWithType();

		// Act
		SkillDTO dto = SkillMapper.toDTO(entity);

		// Assert
		SkillAssertions.assertEqualsProperties(entity, dto);

	}

	@Test
	@DisplayName("Test de conversion d'une entité compétence null en objet de transfert")
	public void testEntityToDTO_ReturnNullDTO() {
		// Arrange set to Null + Act
		SkillDTO dto = SkillMapper.toDTO(null);

		// Assert
		Assertions.assertNull(dto);
	}

	@Test
	@DisplayName("Test de conversion d'une liste d'entités compétence en liste d'objets de transfert")
	public void testEntityListToDTOList_ReturnCompleteDTOList() {
		// Arrange
		TestDatas<Skill, SkillDTO> datas = SkillAssertions.getSkillTestDatas(5);

		// Act
		List<SkillDTO> listDTO = SkillMapper.toDTOList(datas.entities);

		// Assert
		SkillAssertions.assertListEntitiesDTOs(datas.entities, listDTO);
	}

	@Test
	@DisplayName("Test de conversion d'une liste d'entités compétence null en liste d'objets de transfert")
	public void testEntityListToDTOList_ReturnNullDTOList() {
		// Arrange set to Null + Act + Assert
		Assertions.assertNull(SkillMapper.toDTOList(null));
	}

	@Test
	@DisplayName("Test de conversion d'un objet de transfert compétence en entité")
	public void testDTOToEntity_ReturnCompleteEntity() {
		// Arrange
		SkillDTO dto = SkillAssertions.getSkillDTO();

		// Act
		Skill entity = SkillMapper.toEntity(dto);

		// Assert
		SkillAssertions.assertEqualsProperties(entity, dto);
	}

	@Test
	@DisplayName("Test de conversion d'un objet de transfert compétence null en entité")
	public void testDTOToEntity_ReturnNullEntity() {
		// Arrange set to null + Act
		Skill entity = SkillMapper.toEntity(null);

		// Assert
		Assertions.assertNull(entity);
	}

	@Test
	@DisplayName("Test de conversion d'une liste d'objets de transfert compétence en liste d'entités")
	public void testDTOListToEntityList_ReturnCompleteEntityList() {
		// Arrange
		TestDatas<Skill, SkillDTO> datas = SkillAssertions.getSkillTestDatas(5);

		// Act
		List<Skill> listEntities = SkillMapper.toEntityList(datas.dtos);

		// Assert
		SkillAssertions.assertListEntitiesDTOs(listEntities, datas.dtos);
	}

	@Test
	@DisplayName("Test de conversion d'une liste d'objets de transfert compétence null en liste d'entités")
	public void testDTOListToEntityList_ReturnNullEntityList() {
		// Arrange set to Null + Act + Assert
		Assertions.assertNull(SkillMapper.toEntityList(null));
	}

}

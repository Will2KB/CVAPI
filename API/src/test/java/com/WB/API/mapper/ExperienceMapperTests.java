package com.WB.API.mapper;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.WB.API.assertions.ExperienceAssertions;
import com.WB.API.assertions.TestSummaryDatas;
import com.WB.API.dto.ExperienceDTO;
import com.WB.API.dto.ExperienceSummaryDTO;
import com.WB.API.model.Experience;

@ActiveProfiles("test")
@DataJpaTest
@DisplayName("Test du mapper des expériences")
public class ExperienceMapperTests {

	@Test
	@DisplayName("Test de conversion d'une entité résumé d'expérience sans sous objet en objet de transfert")
	public void testEntityToSummaryDTO_ReturnSummaryDTOWithOutSubObject() {
		// Arrange
		Experience entity = ExperienceAssertions.getExperience();

		// Act
		ExperienceSummaryDTO summary = ExperienceMapper.toSummaryDTO(entity);

		// Assert
		ExperienceAssertions.assertEqualsProperties(entity, summary);
	}

	@Test
	@DisplayName("Test de conversion d'une entité d'expérience avec un établissement en objet de transfert résumé")
	public void testEntityToSummaryDTO_ReturnSummaryDTOWithEstablishement() {
		// Arrange
		Experience entity = ExperienceAssertions.getExperienceWithEstablishement();

		// Act
		ExperienceSummaryDTO summary = ExperienceMapper.toSummaryDTO(entity);

		// Assert
		ExperienceAssertions.assertEqualsProperties(entity, summary);
	}

	@Test
	@DisplayName("Test de conversion d'une entité d'expérience avec une ville sans pays en objet de transfert résumé")
	public void testEntityToSummaryDTO_ReturnSummaryDTOWithCityWithoutCountry() {
		// Arrange
		Experience entity = ExperienceAssertions.getExperienceWithCity();

		// Act
		ExperienceSummaryDTO summary = ExperienceMapper.toSummaryDTO(entity);

		// Assert
		ExperienceAssertions.assertEqualsProperties(entity, summary);
	}

	@Test
	@DisplayName("Test de conversion d'une entité d'expérience avec un pays en objet de transfert résumé")
	public void testEntityToSummaryDTO_ReturnSummaryDTOWithCountry() {
		// Arrange
		Experience entity = ExperienceAssertions.getExperienceWithCountry();

		// Act
		ExperienceSummaryDTO summary = ExperienceMapper.toSummaryDTO(entity);

		// Assert
		ExperienceAssertions.assertEqualsProperties(entity, summary);
	}

	@Test
	@DisplayName("Test de conversion d'une entité d'expérience null en objet de transfert résumé")
	public void testEntityToSummaryDTO_ReturnNullSummaryDTO() {
		// Arrange set to Null + Act
		ExperienceSummaryDTO summary = ExperienceMapper.toSummaryDTO(null);

		// Assert
		Assertions.assertNull(summary);
	}

	@Test
	@DisplayName("Test de conversion d'une entité expérience sans sous objet en objet de transfert")
	public void testEntityToDTO_ReturnDTOWithOutSubObject() {
		// Arrange
		Experience entity = ExperienceAssertions.getExperience();

		// Act
		ExperienceDTO dto = ExperienceMapper.toDTO(entity);

		// Assert
		ExperienceAssertions.assertEqualsProperties(entity, dto, true);
	}

	@Test
	@DisplayName("Test de conversion d'une entité d'expérience avec un établissement en objet de transfert")
	public void testEntityToDTO_ReturnDTOWithEstablishement() {
		// Arrange
		Experience entity = ExperienceAssertions.getExperienceWithEstablishement();

		// Act
		ExperienceDTO dto = ExperienceMapper.toDTO(entity);

		// Assert
		ExperienceAssertions.assertEqualsProperties(entity, dto, true);
	}

	@Test
	@DisplayName("Test de conversion d'une entité d'expérience avec une ville sans pays en objet de transfert")
	public void testEntityToDTO_ReturnDTOWithCityWithoutCountry() {
		// Arrange
		Experience entity = ExperienceAssertions.getExperienceWithCity();

		// Act
		ExperienceDTO dto = ExperienceMapper.toDTO(entity);

		// Assert
		ExperienceAssertions.assertEqualsProperties(entity, dto, false);
	}

	@Test
	@DisplayName("Test de conversion d'une entité d'expérience avec un pays en objet de transfert")
	public void testEntityToDTO_ReturnDTOWithCountry() {
		// Arrange
		Experience entity = ExperienceAssertions.getExperienceWithCountry();

		// Act
		ExperienceDTO dto = ExperienceMapper.toDTO(entity);

		// Assert
		ExperienceAssertions.assertEqualsProperties(entity, dto, false);
	}

	@Test
	@DisplayName("Test de conversion d'une entité expérience null en objet de transfert")
	public void testEntityToDTO_ReturnNullDTO() {
		// Arrange set to Null + Act
		ExperienceDTO dto = ExperienceMapper.toDTO(null);

		// Assert
		Assertions.assertNull(dto);
	}

	@Test
	@DisplayName("Test de conversion d'une liste d'entités expérience en liste d'objets de transfert résumé")
	public void testEntityListToSummaryDTOList_ReturnCompleteDTOList() {
		// Arrange
		TestSummaryDatas<Experience, ExperienceDTO, ExperienceSummaryDTO> datas = ExperienceAssertions
				.getExperienceTestDatas(5);

		// Act
		List<ExperienceSummaryDTO> listSummaryDTO = ExperienceMapper.toSummaryDTOList(datas.entities);

		// Assert
		ExperienceAssertions.assertListEntitiesSummaries(datas.entities, listSummaryDTO);
	}

	@Test
	@DisplayName("Test de conversion d'une liste d'entités expérience null en liste d'objets de transfert résumé")
	public void testEntityListToSummaryDTOList_ReturnNullDTOList() {
		// Arrange set to Null + Act + Assert
		Assertions.assertNull(ExperienceMapper.toSummaryDTOList(null));
	}

	@Test
	@DisplayName("Test de conversion d'une liste d'entités expérience en liste d'objets de transfert")
	public void testEntityListToDTOList_ReturnCompleteDTOList() {
		// Arrange
		TestSummaryDatas<Experience, ExperienceDTO, ExperienceSummaryDTO> datas = ExperienceAssertions
				.getExperienceTestDatas(5);

		// Act
		List<ExperienceDTO> listDTO = ExperienceMapper.toDTOList(datas.entities);

		// Assert
		ExperienceAssertions.assertListEntitiesDTOs(datas.entities, listDTO, true);
	}

	@Test
	@DisplayName("Test de conversion d'une liste d'entités expérience null en liste d'objets de transfert")
	public void testEntityListToDTOList_ReturnNullDTOList() {
		// Arrange set to Null + Act + Assert
		Assertions.assertNull(ExperienceMapper.toDTOList(null));
	}

	@Test
	@DisplayName("Test de conversion d'un objet de transfert expérience en entité")
	public void testDTOToEntity_ReturnCompleteDTO() {
		// Arrange
		ExperienceDTO dto = ExperienceAssertions.getExperienceDTO();

		// Act
		Experience entity = ExperienceMapper.toEntity(dto);

		// Assert
		ExperienceAssertions.assertEqualsProperties(entity, dto, true);
	}

	@Test
	@DisplayName("Test de conversion d'un objet de transfert expérience null en entité")
	public void testDTOToEntity_ReturnNullDTO() {
		// Arrange set to Null + Act
		Experience entity = ExperienceMapper.toEntity((ExperienceDTO) null);

		// Assert
		Assertions.assertNull(entity);
	}

	@Test
	@DisplayName("Test de conversion d'un objet de transfert résumé d'expérience en entité")
	public void testDTOSummaryToEntity_ReturnCompleteEntity() {
		// Arrange
		ExperienceDTO dto = ExperienceAssertions.getExperienceDTO();

		// Act
		Experience entity = ExperienceMapper.toEntity(dto.getSummary());

		// Assert
		ExperienceAssertions.assertEqualsProperties(entity, dto.getSummary());

	}

	@Test
	@DisplayName("Test de conversion d'un objet de transfert résumé d'expérience null en entité")
	public void testDTOSummaryToEntity_ReturnNullEntity() {
		// Arrange set to Null + Act
		Experience entity = ExperienceMapper.toEntity((ExperienceSummaryDTO) null);

		// Assert
		Assertions.assertNull(entity);

	}

	@Test
	@DisplayName("Test de conversion d'une liste d'objets de transfert expérience en liste d'entités")
	public void testDTOListToEntityList_ReturnCompleteEntityList() {
		// Arrange
		TestSummaryDatas<Experience, ExperienceDTO, ExperienceSummaryDTO> datas = ExperienceAssertions
				.getExperienceTestDatas(5);

		// Act
		List<Experience> listEntities = ExperienceMapper.toEntityListFromDTO(datas.dtos);

		// Assert
		ExperienceAssertions.assertListEntitiesDTOs(listEntities, datas.dtos, true);
	}

	@Test
	@DisplayName("Test de conversion d'une liste d'objets de transfert expérience null en liste d'entités")
	public void testDTOListToEntityList_ReturnNullEntityList() {
		// Arrange set to Null + Act + Assert
		Assertions.assertNull(ExperienceMapper.toEntityListFromDTO(null));
	}

	@Test
	@DisplayName("Test de conversion d'une liste d'objets de transfert résumé d'expérience en liste d'entités")
	public void testDTOSummaryListToEntityList_ReturnCompleteEntityList() {
		// Arrange
		TestSummaryDatas<Experience, ExperienceDTO, ExperienceSummaryDTO> datas = ExperienceAssertions
				.getExperienceTestDatas(5);

		// Act
		List<Experience> listEntities = ExperienceMapper.toEntityListFromSummary(datas.dtoSummarries);

		// Assert
		ExperienceAssertions.assertListEntitiesSummaries(listEntities, datas.dtoSummarries);
	}

	@Test
	@DisplayName("Test de conversion d'une liste d'objets de transfert résumé d'expérience null en liste d'entités")
	public void testDTOSummaryListToEntityList_ReturnNullEntityList() {
		// Arrange set to Null + Act + Assert
		Assertions.assertNull(ExperienceMapper.toEntityListFromSummary(null));
	}

}

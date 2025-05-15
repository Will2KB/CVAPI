package com.WB.API.service;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import com.WB.API.assertions.SkillTypeAssertions;
import com.WB.API.assertions.TestDatas;
import com.WB.API.dto.SkillTypeDTO;
import com.WB.API.exceptions.RessourceNotFoundException;
import com.WB.API.model.SkillType;
import com.WB.API.repository.SkillTypeRepository;

/*
 * Tests du service des types de compétence
 * Lors de ces test, nous souhaitons vérifier le fonctionnement du service 
 * c'est pourquoi le comportement du repository (auquel le service fait appel) sera mocké
 */
@ActiveProfiles("test")
@DisplayName("Test du service des types de compétence")
@ExtendWith(MockitoExtension.class)
class SkillTypeServiceTests {

	@Mock
	private SkillTypeRepository skillTypeRepository;

	@InjectMocks
	private SkillTypeService skillTypeService;

	@Test
	@DisplayName("Recherche d'un type de compétence à partir de l'ID")
	void findById_ShouldReturnCorrectSkillType() {
		// Arrange
		SkillTypeDTO mockedSkillTypeDTO = SkillTypeAssertions.getSkillTypeDTO();
		SkillType mockedSkillType = new SkillType(mockedSkillTypeDTO.getId());
		mockedSkillType.setName(mockedSkillTypeDTO.getName());
		Mockito.when(skillTypeRepository.findById(mockedSkillType.getId())).thenReturn(Optional.of(mockedSkillType));

		// Act
		SkillTypeDTO result = skillTypeService.getSkillTypeByID(mockedSkillType.getId());

		// Assert
		SkillTypeAssertions.assertNotNullDTO(result);
		SkillTypeAssertions.assertEqualsProperties(mockedSkillType, result);
	}

	@Test
	@DisplayName("Recherche d'un type de compétence inexistant retourne null")
	void findById_ShouldReturnNullWhenNotFound() {
		// Arrange
		Integer id = 999;
		Mockito.when(skillTypeRepository.findById(id)).thenReturn(Optional.empty());

		// Act + Assert
		RessourceNotFoundException exception = Assertions.assertThrows(RessourceNotFoundException.class,
				() -> skillTypeService.getSkillTypeByID(id));
		// Vérification du message de l'exception
		Assertions.assertEquals("Skill type not found with id: " + id, exception.getMessage());
	}

	@Test
	@DisplayName("Chargement de toutes les types de compétences")
	void getSkillTypes_ShouldReturnListOfAllSkillTypes() {
		// Arrange
		TestDatas<SkillType, SkillTypeDTO> datas = SkillTypeAssertions.getSkillTypeTestDatas(6);
		Mockito.when(skillTypeRepository.findAll()).thenReturn(datas.entities);

		// Act
		List<SkillTypeDTO> loadedSkillTypes = skillTypeService.getSkillTypes();

		// Assert
		SkillTypeAssertions.assertListEntitiesDTOs(datas.entities, loadedSkillTypes);
	}

	@Test
	@DisplayName("Chargement des types de compétence quand la base est vide")
	void getSkillTypes_ShouldReturnEmptyList() {
		// Arrange
		Mockito.when(skillTypeRepository.findAll()).thenReturn(List.of());

		// Act + Assert
		RessourceNotFoundException exception = Assertions.assertThrows(RessourceNotFoundException.class,
				() -> skillTypeService.getSkillTypes());

		// Vérification du message de l'exception
		Assertions.assertEquals("List of skill types is empty.", exception.getMessage());
	}
}
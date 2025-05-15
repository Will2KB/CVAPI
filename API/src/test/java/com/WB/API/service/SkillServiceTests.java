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

import com.WB.API.assertions.SkillAssertions;
import com.WB.API.assertions.TestDatas;
import com.WB.API.dto.SkillDTO;
import com.WB.API.exceptions.RessourceNotFoundException;
import com.WB.API.model.Skill;
import com.WB.API.repository.SkillRepository;

/*
 * Tests du service des compétences
 * Lors de ces test, nous souhaitons vérifier le fonctionnement du service 
 * c'est pourquoi le comportement du repository (auquel le service fait appel) sera mocké
 */
@ActiveProfiles("test")
@DisplayName("Test du service des compétences")
@ExtendWith(MockitoExtension.class)
class SkillServiceTests {

	@Mock
	private SkillRepository skillRepository;

	@InjectMocks
	private SkillService skillService;

	@Test
	@DisplayName("Recherche d'une compétence à partir de l'ID")
	void findById_ShouldReturnCorrectSkill() {
		// Arrange
		SkillDTO mockedSkillDTO = SkillAssertions.getSkillDTO();
		Skill mockedSkill = new Skill(mockedSkillDTO.getId(), mockedSkillDTO.getName(), mockedSkillDTO.isEnable());
		Mockito.when(skillRepository.findById(mockedSkill.getId())).thenReturn(Optional.of(mockedSkill));

		// Act
		SkillDTO result = skillService.getSkillByID(mockedSkill.getId());

		// Assert
		SkillAssertions.assertEqualsProperties(mockedSkill, result);
	}

	@Test
	@DisplayName("Recherche d'une compétence à partir d'un ID inexistant")
	void findById_ShouldReturnNullWhenNotFound() {
		// Arrange
		Integer id = 999;
		Mockito.when(skillRepository.findById(id)).thenReturn(Optional.empty());

		// Act + Assert
		RessourceNotFoundException exception = Assertions.assertThrows(RessourceNotFoundException.class,
				() -> skillService.getSkillByID(id));

		// Vérification du message de l'exception
		Assertions.assertEquals("Skill not found with id: " + id, exception.getMessage());
	}

	@Test
	@DisplayName("Chargement de toutes les compétences")
	void getSkills_ShouldReturnListOfAllSkills() {
		// Arrange
		TestDatas<Skill, SkillDTO> datas = SkillAssertions.getSkillTestDatas(6);
		Mockito.when(skillRepository.findAll()).thenReturn(datas.entities);

		// Act
		List<SkillDTO> loadedSkills = skillService.getSkills();

		// Assert
		SkillAssertions.assertListDTOs(datas.dtos, loadedSkills);
	}

	@Test
	@DisplayName("Chargement de toutes les compétences quand la base est vide")
	void getSkills_ShouldReturnEmptyList() {
		// Arrange
		Mockito.when(skillRepository.findAll()).thenReturn(List.of());

		// Act + Assert
		RessourceNotFoundException exception = Assertions.assertThrows(RessourceNotFoundException.class,
				() -> skillService.getSkills());

		// Vérification du message de l'exception
		Assertions.assertEquals("List of skills is empty.", exception.getMessage());

	}
}
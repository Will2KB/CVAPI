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

import com.WB.API.assertions.ExperienceAssertions;
import com.WB.API.assertions.TestSummaryDatas;
import com.WB.API.dto.ExperienceDTO;
import com.WB.API.dto.ExperienceSummaryDTO;
import com.WB.API.exceptions.RessourceNotFoundException;
import com.WB.API.model.Experience;
import com.WB.API.repository.ExperienceRepository;

/*
 * Tests du service des expériences
 * Lors de ces test, nous souhaitons vérifier le fonctionnement du service 
 * c'est pourquoi le comportement du repository (auquel le service fait appel) sera mocké
 */
@ActiveProfiles("test")
@DisplayName("Test du service des expériences")
@ExtendWith(MockitoExtension.class)
class ExperienceServiceTest {

	@Mock
	private ExperienceRepository experienceRepository;

	@InjectMocks
	private ExperienceService experienceService;

	@Test
	@DisplayName("Sauvegarde d'une Experiencene")
	void saveExperience_ReturnsSavedExperience() {
		// Arrange
		ExperienceDTO mockedExperience = ExperienceAssertions.getExperienceDTO();
		Experience input = new Experience(mockedExperience.getId(), mockedExperience.getName(),
				mockedExperience.getDateBeginning(), mockedExperience.getDateEnding(), mockedExperience.isFormation(),
				mockedExperience.getMission());

		Mockito.when(experienceRepository.save(Mockito.any(Experience.class))).thenReturn(input);

		// Act
		ExperienceSummaryDTO result = experienceService.saveExperience(mockedExperience);

		// Assert
		ExperienceAssertions.assertNotNullDTO(result);
		ExperienceAssertions.assertEqualsProperties(input, result);
	}

	@Test
	@DisplayName("Recherche d'une Experiencene à partir de l'ID")
	void findById_ShouldReturnCorrectExperience() {
		// Arrange
		ExperienceDTO mockedExperienceDTO = ExperienceAssertions.getExperienceDTO();
		Experience mockedExperience = new Experience(mockedExperienceDTO.getId(), mockedExperienceDTO.getName(),
				mockedExperienceDTO.getDateBeginning(), mockedExperienceDTO.getDateEnding(),
				mockedExperienceDTO.isFormation(), mockedExperienceDTO.getMission());
		Mockito.when(experienceRepository.findById(mockedExperienceDTO.getId()))
				.thenReturn(Optional.of(mockedExperience));

		// Act
		ExperienceDTO result = experienceService.getExperienceById(mockedExperienceDTO.getId());

		// Assert
		ExperienceAssertions.assertNotNullDTO(result);
		ExperienceAssertions.assertEqualsProperties(mockedExperienceDTO, result, true);
	}

	@Test
	@DisplayName("Recherche d'une Experiencene à partir d'un ID inexistant")
	void findById_ShouldReturnNullWhenNotFound() {
		// Arrange
		Integer id = 999;
		Mockito.when(experienceRepository.findById(id)).thenReturn(Optional.empty());

		// Act + Assert
		RessourceNotFoundException exception = Assertions.assertThrows(RessourceNotFoundException.class,
				() -> experienceService.getExperienceById(id));

		// Vérification du message de l'exception
		Assertions.assertEquals("Experience not found with id: " + id, exception.getMessage());
	}

	@Test
	@DisplayName("Chargement de toutes les Experiences")
	void findAll_ShouldReturnListOfAllExperiences() {
		// Arrange
		TestSummaryDatas<Experience, ExperienceDTO, ExperienceSummaryDTO> datas = ExperienceAssertions
				.getExperienceTestDatas(5);
		Mockito.when(experienceRepository.findAll()).thenReturn(datas.entities);

		// Act
		List<ExperienceSummaryDTO> loadedExperiences = experienceService.getExperiences();

		// Assert
		ExperienceAssertions.assertListEntitiesSummaries(datas.entities, loadedExperiences);
	}

	@Test
	@DisplayName("Chargement d'une liste vide si la base de données est vide")
	void findAll_ShouldReturnEmptyListWhenBDDEmpty() {
		// Arrange
		Mockito.when(experienceRepository.findAll()).thenReturn(List.of());

		// Act + Assert
		RessourceNotFoundException exception = Assertions.assertThrows(RessourceNotFoundException.class,
				() -> experienceService.getExperiences());

		// Vérification du message de l'exception
		Assertions.assertEquals("List of summaries of experience is empty.", exception.getMessage());

	}
}
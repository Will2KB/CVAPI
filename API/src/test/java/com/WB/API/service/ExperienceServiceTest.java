package com.WB.API.service;

import java.util.List;
import java.util.Optional;

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
import com.WB.API.model.Experience;
import com.WB.API.repository.ExperienceRepository;

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

		ExperienceDTO mockedExperience = ExperienceAssertions.getExperienceDTO();
		Experience input = new Experience(mockedExperience.getId(), mockedExperience.getName(),
				mockedExperience.getDateBeginning(), mockedExperience.getDateEnding(), mockedExperience.isFormation());

		Mockito.when(experienceRepository.save(Mockito.any(Experience.class))).thenReturn(input);

		ExperienceSummaryDTO result = experienceService.saveExperience(mockedExperience);

		ExperienceAssertions.assertNotNullDTO(result);
		ExperienceAssertions.assertEqualsProperties(input, result);
	}

	@Test
	@DisplayName("Recherche d'une Experiencene à partir de l'ID")
	void findById_ShouldReturnCorrectExperience() {
		ExperienceDTO mockedExperienceDTO = ExperienceAssertions.getExperienceDTO();
		Experience mockedExperience = new Experience(mockedExperienceDTO.getId(), mockedExperienceDTO.getName(),
				mockedExperienceDTO.getDateBeginning(), mockedExperienceDTO.getDateEnding(),
				mockedExperienceDTO.isFormation());
		Mockito.when(experienceRepository.findById(mockedExperienceDTO.getId()))
				.thenReturn(Optional.of(mockedExperience));
		ExperienceDTO result = experienceService.getExperienceById(mockedExperienceDTO.getId());

		ExperienceAssertions.assertNotNullDTO(result);
		ExperienceAssertions.assertEqualsProperties(mockedExperienceDTO, result);
	}

	@Test
	@DisplayName("Chargement de toutes les Experiences")
	void findAll_ShouldReturnListOfAllExperiences() {
		TestSummaryDatas<Experience, ExperienceDTO, ExperienceSummaryDTO> datas = ExperienceAssertions
				.getSkillTestDatas(5);
		Mockito.when(experienceRepository.findAll()).thenReturn(datas.entities);

		List<ExperienceSummaryDTO> loadedExperiences = experienceService.getExperiences();

		ExperienceAssertions.assertListEntitiesSummaries(datas.entities, loadedExperiences);
	}
}
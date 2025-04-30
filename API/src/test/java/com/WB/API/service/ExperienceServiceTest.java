package com.WB.API.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import com.WB.API.dto.ExperienceDTO;
import com.WB.API.dto.ExperienceSummaryDTO;
import com.WB.API.mapper.ExperienceMapper;
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

	private List<ExperienceDTO> mockedListExperiences;

	@BeforeEach
	private void LoadData() {
		mockedListExperiences = new ArrayList<>();
		mockedListExperiences
				.add(new ExperienceDTO(12, "Exp. 1", LocalDate.of(2012, 12, 15), LocalDate.of(2016, 2, 5), false));
		mockedListExperiences
				.add(new ExperienceDTO(13, "Exp. 2", LocalDate.of(2012, 12, 15), LocalDate.of(2016, 2, 5), false));
		mockedListExperiences
				.add(new ExperienceDTO(14, "Exp. 3", LocalDate.of(2012, 12, 15), LocalDate.of(2016, 2, 5), false));
		mockedListExperiences
				.add(new ExperienceDTO(15, "Form. 1", LocalDate.of(2001, 9, 1), LocalDate.of(2005, 9, 1), true));
		mockedListExperiences
				.add(new ExperienceDTO(16, "Form. 2", LocalDate.of(2006, 9, 1), LocalDate.of(2010, 9, 5), true));

	}

	private List<Experience> getMockedListPersonEntity() {
		List<Experience> experiences = new ArrayList<>();

		for (ExperienceDTO experienceDTO : mockedListExperiences) {
			experiences.add(ExperienceMapper.toEntity(experienceDTO));
		}

		return experiences;
	}

	@Test
	@DisplayName("Sauvegarde d'une Experiencene")
	void saveExperience_ReturnsSavedExperience() {

		ExperienceDTO mockedExperience = mockedListExperiences.get(2);
		Experience input = new Experience(mockedExperience.getId(), mockedExperience.getName(),
				mockedExperience.getDateBeginning(), mockedExperience.getDateEnding(), mockedExperience.isFormation());

		Mockito.when(experienceRepository.save(Mockito.any(Experience.class))).thenReturn(input);

		ExperienceSummaryDTO result = experienceService.saveExperience(mockedExperience);

		Assertions.assertNotNull(result.getId());
		Assertions.assertEquals(input.getName(), result.getName());
		Assertions.assertEquals(input.getDateBeginning(), result.getDateBeginning());
		Assertions.assertEquals(input.getDateEnding(), result.getDateEnding());
		Assertions.assertEquals(input.isFormation(), result.isFormation());
	}

	@Test
	@DisplayName("Recherche d'une Experiencene à partir de l'ID")
	void findById_ShouldReturnCorrectExperience() {
		ExperienceDTO mockedExperienceDTO = mockedListExperiences.get(2);
		Experience mockedExperience = new Experience(mockedExperienceDTO.getId(), mockedExperienceDTO.getName(),
				mockedExperienceDTO.getDateBeginning(), mockedExperienceDTO.getDateEnding(),
				mockedExperienceDTO.isFormation());
		Mockito.when(experienceRepository.findById(mockedExperienceDTO.getId()))
				.thenReturn(Optional.of(mockedExperience));
		ExperienceDTO result = experienceService.getExperienceById(mockedExperienceDTO.getId());

		Assertions.assertNotNull(result);
		Assertions.assertEquals(mockedExperienceDTO.getName(), result.getName());
		Assertions.assertEquals(mockedExperienceDTO.getDateBeginning(), result.getDateBeginning());
		Assertions.assertEquals(mockedExperienceDTO.getDateEnding(), result.getDateEnding());
		Assertions.assertEquals(mockedExperienceDTO.isFormation(), result.isFormation());
	}

	@Test
	@DisplayName("Chargement de toutes les Experiences")
	void findAll_ShouldReturnListOfAllExperiences() {
		Mockito.when(experienceRepository.findAll()).thenReturn(this.getMockedListPersonEntity());

		List<ExperienceSummaryDTO> loadedExperiences = experienceService.getExperiences();

		for (ExperienceDTO expectedExperience : mockedListExperiences) {
			ExperienceSummaryDTO actualExperience = loadedExperiences.stream()
					.filter(c -> c.getName().equals(expectedExperience.getName())).findFirst().orElse(null);

			Assertions.assertNotNull(actualExperience);
			Assertions.assertEquals(expectedExperience.getId(), actualExperience.getId());
			Assertions.assertEquals(expectedExperience.getName(), actualExperience.getName());
			Assertions.assertEquals(expectedExperience.getDateBeginning(), actualExperience.getDateBeginning());
			Assertions.assertEquals(expectedExperience.getDateEnding(), actualExperience.getDateEnding());
			Assertions.assertEquals(expectedExperience.isFormation(), actualExperience.isFormation());

		}
	}
}
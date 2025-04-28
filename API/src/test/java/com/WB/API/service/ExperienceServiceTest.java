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

	private List<Experience> mockedListExperiences;

	@BeforeEach
	private void LoadData() {
		mockedListExperiences = new ArrayList<Experience>();
		mockedListExperiences
				.add(new Experience(12, "Exp. 1", LocalDate.of(2012, 12, 15), LocalDate.of(2016, 2, 5), false));
		mockedListExperiences
				.add(new Experience(13, "Exp. 2", LocalDate.of(2012, 12, 15), LocalDate.of(2016, 2, 5), false));
		mockedListExperiences
				.add(new Experience(14, "Exp. 3", LocalDate.of(2012, 12, 15), LocalDate.of(2016, 2, 5), false));
		mockedListExperiences
				.add(new Experience(15, "Form. 1", LocalDate.of(2001, 9, 1), LocalDate.of(2005, 9, 1), true));
		mockedListExperiences
				.add(new Experience(16, "Form. 2", LocalDate.of(2006, 9, 1), LocalDate.of(2010, 9, 5), true));

	}

	@Test
	@DisplayName("Sauvegarde d'une Experiencene")
	void saveExperience_ReturnsSavedExperience() {

		Experience mockedExperience = mockedListExperiences.get(2);
		Experience input = new Experience(mockedExperience.getId(), mockedExperience.getName(),
				mockedExperience.getDateBegining(), mockedExperience.getDateEnding(), mockedExperience.isFormation());

		Mockito.when(experienceRepository.save(input)).thenReturn(mockedExperience);

		Experience result = experienceService.saveExperience(input);

		Assertions.assertNotNull(result.getId());
		Assertions.assertEquals(input.getName(), result.getName());
		Assertions.assertEquals(input.getDateBegining(), result.getDateBegining());
		Assertions.assertEquals(input.getDateEnding(), result.getDateEnding());
		Assertions.assertEquals(input.isFormation(), result.isFormation());
	}

	@Test
	@DisplayName("Recherche d'une Experiencene à partir de l'ID")
	void findById_ShouldReturnCorrectExperience() {
		Experience mockedExperience = mockedListExperiences.get(2);
		Mockito.when(experienceRepository.findById(mockedExperience.getId())).thenReturn(Optional.of(mockedExperience));
		Experience result = experienceService.getExperienceById(mockedExperience.getId());

		Assertions.assertNotNull(result);
		Assertions.assertEquals(mockedExperience.getName(), result.getName());
		Assertions.assertEquals(mockedExperience.getDateBegining(), result.getDateBegining());
		Assertions.assertEquals(mockedExperience.getDateEnding(), result.getDateEnding());
		Assertions.assertEquals(mockedExperience.isFormation(), result.isFormation());
	}

	@Test
	@DisplayName("Chargement de toutes les Experiences")
	void findAll_ShouldReturnListOfAllExperiences() {
		Mockito.when(experienceRepository.findAll()).thenReturn(mockedListExperiences);

		List<Experience> loadedExperiences = experienceService.getExperiences();

		for (Experience expectedExperience : mockedListExperiences) {
			Experience actualExperience = loadedExperiences.stream()
					.filter(c -> c.getName().equals(expectedExperience.getName())).findFirst().orElse(null);

			Assertions.assertNotNull(actualExperience);
			Assertions.assertEquals(expectedExperience.getId(), actualExperience.getId());
			Assertions.assertEquals(expectedExperience.getName(), actualExperience.getName());
			Assertions.assertEquals(expectedExperience.getDateBegining(), actualExperience.getDateBegining());
			Assertions.assertEquals(expectedExperience.getDateEnding(), actualExperience.getDateEnding());
			Assertions.assertEquals(expectedExperience.isFormation(), actualExperience.isFormation());

		}
	}
}
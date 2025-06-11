package com.WB.API.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.WB.API.assertions.ExperienceAssertions;
import com.WB.API.model.Experience;

/*
 * Tests du repository des expériences
 * Lors de ces test, nous souhaitons vérifier le fonctionnement du repository 
 * Ces tests seront effectué sur une base de test H2
 */
@ActiveProfiles("test")
@DataJpaTest
@DisplayName("Test du repository des expériences")
class ExperienceRepositoryTests {

	@Autowired
	ExperienceRepository experienceRepository;

	private List<Experience> experiences;

	/**
	 * Chargement des données de tests spécifique au repository
	 */
	@BeforeEach
	private void loadData() {
		experiences = new ArrayList<>();

		Experience experience1 = new Experience();
		experience1.setName("Université");
		experience1.setDateBegining(LocalDate.of(2008, 9, 15));
		experience1.setDateEnding(LocalDate.of(2011, 9, 1));
		experience1.setDescriptions(new ArrayList<>(List.of("Etude en informatique")));
		experience1.setFormation(true);
		experience1.setMission("Découverte du développement");
		experienceRepository.save(experience1);
		experiences.add(experience1);

		Experience experience2 = new Experience();
		experience2.setName("Première exp.");
		experience2.setDateBegining(LocalDate.of(2011, 12, 15));
		experience2.setDateEnding(LocalDate.of(2016, 2, 1));
		experience2.setDescriptions(new ArrayList<>(List.of("Bio-informatique")));
		experience2.setFormation(false);
		experience2.setMission("Envoie des résultats aux clients");
		experienceRepository.save(experience2);
		experiences.add(experience2);

	}

	@Test
	@DisplayName("Sauvegarde d'une nouvelle experience")
	void saveExperience_RetrieveIt() {
		// Arrange
		Experience newExperience = new Experience();
		newExperience.setName("Univ. 2");
		newExperience.setDateBegining(LocalDate.of(2011, 12, 15));
		newExperience.setDateEnding(LocalDate.of(2016, 2, 1));
		newExperience.setDescriptions(new ArrayList<>(List.of("Biologie")));
		newExperience.setFormation(true);
		newExperience.setMission("Envoie des résultats aux clients");

		// Act
		Experience savedExperience = experienceRepository.save(newExperience);

		// Assert
		ExperienceAssertions.assertNotNullEntity(savedExperience);
		Assertions.assertTrue(savedExperience.getId() > 0);

		// Vérification que l'expérience est bien en base
		Optional<Experience> optExperience = experienceRepository.findById(savedExperience.getId());
		Assertions.assertTrue(optExperience.isPresent());
		Experience findExperience = optExperience.get();
		ExperienceAssertions.assertEqualsProperties(findExperience, newExperience, true);
		Assertions.assertEquals(newExperience.getName(), findExperience.getName());
	}

	@Test
	@DisplayName("Chargement d'une expérience à partir de son ID")
	void findExperienceById_ReturnCorrectExperience() {
		// Arrange
		Experience searchExperience = experiences.get(0);

		// Act
		Optional<Experience> optExperience = experienceRepository.findById(searchExperience.getId());

		// Assert
		Assertions.assertTrue(optExperience.isPresent(),
				"L'expérience n'a pas été trouvée pour l'ID " + searchExperience.getId());
		Experience experience = optExperience.get();
		ExperienceAssertions.assertEqualsProperties(searchExperience, experience, true);
	}

	@Test
	@DisplayName("Chargement d'une expérience à partir d'un ID inexistant")
	void findExperienceById_ReturnNullWhenNotFound() {
		// Arrange
		Integer id = 999;

		// Act
		Optional<Experience> optExperience = experienceRepository.findById(id);

		// Assert
		Assertions.assertFalse(optExperience.isPresent(), "L'expérience a été trouvée pour l'ID " + id);
	}

	@Test
	@DisplayName("Chargement de toutes les expériences")
	void findAllExperiences_ReturnCorrectList() {
		// Arrange @BeforeEach

		// Act
		List<Experience> findExperiences = experienceRepository.findAll();

		// Assert
		for (Experience expectedExperience : experiences) {
			Experience actualExperience = findExperiences.stream()
					.filter(c -> c.getName().equals(expectedExperience.getName())).findFirst().orElse(null);

			ExperienceAssertions.assertEqualsProperties(expectedExperience, actualExperience, true);
		}
	}
}

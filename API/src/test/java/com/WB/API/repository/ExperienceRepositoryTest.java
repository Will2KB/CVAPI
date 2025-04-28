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

import com.WB.API.model.Experience;

@ActiveProfiles("test")
@DataJpaTest
@DisplayName("Test du repository des expériences")
class ExperienceRepositoryTest {

	@Autowired
	ExperienceRepository experienceRepository;

	private List<Experience> experiences;

	@BeforeEach
	private void loadData() {
		experiences = new ArrayList<>();

		Experience experience1 = new Experience();
		experience1.setName("Université");
		experience1.setDateBegining(LocalDate.of(2008, 9, 15));
		experience1.setDateEnding(LocalDate.of(2011, 9, 1));
		experience1.setDescription("Etude en informatique");
		experience1.setFormation(true);
		experience1.setMission("Découverte du développement");
		experienceRepository.save(experience1);
		experiences.add(experience1);

		Experience experience2 = new Experience();
		experience2.setName("Première exp.");
		experience2.setDateBegining(LocalDate.of(2011, 12, 15));
		experience2.setDateEnding(LocalDate.of(2016, 2, 1));
		experience2.setDescription("Bio-informatique");
		experience2.setFormation(false);
		experience2.setMission("Envoie des résultats aux clients");
		experienceRepository.save(experience2);
		experiences.add(experience2);

	}

	@Test
	@DisplayName("Sauvegarde d'une nouvelle experience")
	void saveExperience_RetrieveIt() {
		Experience newExperience = new Experience();
		newExperience.setName("Univ. 2");
		newExperience.setDateBegining(LocalDate.of(2011, 12, 15));
		newExperience.setDateEnding(LocalDate.of(2016, 2, 1));
		newExperience.setDescription("Biologie");
		newExperience.setFormation(true);
		newExperience.setMission("Envoie des résultats aux clients");
		Experience savedExperience = experienceRepository.save(newExperience);

		Assertions.assertNotNull(savedExperience);
		Assertions.assertNotNull(savedExperience.getId());
		Assertions.assertTrue(savedExperience.getId() > 0);

		// Vérification que l'expérience est bien en base
		Optional<Experience> optExperience = experienceRepository.findById(savedExperience.getId());
		Assertions.assertTrue(optExperience.isPresent());
		Experience findExperience = optExperience.get();
		Assertions.assertEquals(newExperience.getName(), findExperience.getName());
		Assertions.assertEquals(newExperience.getDescription(), findExperience.getDescription());
		Assertions.assertEquals(newExperience.getDateBegining(), findExperience.getDateBegining());
		Assertions.assertEquals(newExperience.getDateEnding(), findExperience.getDateEnding());
		Assertions.assertEquals(newExperience.getMission(), findExperience.getMission());
		Assertions.assertEquals(newExperience.isFormation(), findExperience.isFormation());
	}

	@Test
	@DisplayName("Chargement d'une expérience à partir de son ID")
	void findExperienceById_ReturnCorrectExperience() {
		Experience searchExperience = experiences.get(0);
		Optional<Experience> optExperience = experienceRepository.findById(searchExperience.getId());

		Assertions.assertTrue(optExperience.isPresent(),
				"L'expérience n'a pas été trouvée pour l'ID " + searchExperience.getId());
		Experience experience = optExperience.get();

		Assertions.assertNotNull(experience);
		Assertions.assertEquals(searchExperience.getId(), experience.getId());
		Assertions.assertEquals(searchExperience.getName(), experience.getName());
		Assertions.assertEquals(searchExperience.getDescription(), experience.getDescription());
		Assertions.assertEquals(searchExperience.getDateBegining(), experience.getDateBegining());
		Assertions.assertEquals(searchExperience.getDateEnding(), experience.getDateEnding());
		Assertions.assertEquals(searchExperience.getMission(), experience.getMission());
		Assertions.assertEquals(searchExperience.isFormation(), experience.isFormation());
	}

	@Test
	@DisplayName("Chargement de toutes les expériences")
	void findAllExperiences_ReturnCorrectList() {
		List<Experience> findExperiences = experienceRepository.findAll();

		for (Experience expectedExperience : experiences) {
			Experience actualExperience = findExperiences.stream()
					.filter(c -> c.getName().equals(expectedExperience.getName())).findFirst().orElse(null);

			Assertions.assertNotNull(actualExperience);
			Assertions.assertEquals(expectedExperience.getId(), actualExperience.getId());
			Assertions.assertEquals(expectedExperience.getName(), actualExperience.getName());
			Assertions.assertEquals(expectedExperience.getDescription(), actualExperience.getDescription());
			Assertions.assertEquals(expectedExperience.getDateBegining(), actualExperience.getDateBegining());
			Assertions.assertEquals(expectedExperience.getDateEnding(), actualExperience.getDateEnding());
			Assertions.assertEquals(expectedExperience.getMission(), actualExperience.getMission());
			Assertions.assertEquals(expectedExperience.isFormation(), actualExperience.isFormation());
		}
	}
}

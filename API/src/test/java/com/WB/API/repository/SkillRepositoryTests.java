package com.WB.API.repository;

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

import com.WB.API.assertions.SkillAssertions;
import com.WB.API.model.Skill;

/*
 * Tests du repository des compétences
 * Lors de ces test, nous souhaitons vérifier le fonctionnement du repository 
 * Ces tests seront effectué sur une base de test H2
 */
@ActiveProfiles("test")
@DataJpaTest
@DisplayName("Test du repository des compétences")
class SkillRepositoryTests {

	@Autowired
	private SkillRepository SkillRepository;

	private List<Skill> Skills;

	/**
	 * Chargement des données de tests spécifique au repository
	 */
	@BeforeEach
	void loadData() {

		Skills = new ArrayList<>();

		Skill skill1 = new Skill();
		skill1.setName("Java");
		skill1.setEnable(true);
		SkillRepository.save(skill1);
		Skills.add(skill1);

		Skill skill2 = new Skill();
		skill2.setName("Travaille en équipe");
		skill2.setEnable(true);
		SkillRepository.save(skill2);
		Skills.add(skill2);

		Skill skill3 = new Skill();
		skill3.setName("API");
		skill3.setEnable(false);
		SkillRepository.save(skill3);
		Skills.add(skill3);
	}

	@Test
	@DisplayName("Chargement d'une compétence à partir de son ID")
	void findSkillById_ReturnCorrectSkill() {
		// Arrange
		Skill searchSkill = Skills.get(2);

		// Act
		Optional<Skill> optSkill = SkillRepository.findById(searchSkill.getId());

		// Assert
		Assertions.assertTrue(optSkill.isPresent(),
				"La compétence n'a pas été trouvée pour l'ID " + searchSkill.getId());
		Skill skill = optSkill.get();

		SkillAssertions.assertNotNullEntity(skill);
		SkillAssertions.assertEqualsProperties(searchSkill, skill);

	}

	@Test
	@DisplayName("Chargement d'une compétence à partir d'un ID innexistant")
	void findSkillById_ReturnNullWhenNotFound() {
		// Arrange
		Integer id = 999;

		// Act
		Optional<Skill> optSkill = SkillRepository.findById(id);

		// Assert
		Assertions.assertFalse(optSkill.isPresent(), "La compétence a été trouvée pour l'ID " + id);

	}
}

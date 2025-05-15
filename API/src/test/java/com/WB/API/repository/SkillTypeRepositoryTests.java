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

import com.WB.API.model.SkillType;

/*
 * Tests du repository des types de compétence
 * Lors de ces test, nous souhaitons vérifier le fonctionnement du repository 
 * Ces tests seront effectué sur une base de test H2
 */
@ActiveProfiles("test")
@DataJpaTest
@DisplayName("Test du repository des types de compétence")
class SkillTypeRepositoryTests {

	@Autowired
	private SkillTypeRepository skillTypeRepository;

	private List<SkillType> skillTypes;

	/**
	 * Chargement des données de tests spécifique au repository
	 */
	@BeforeEach
	void loadData() {

		skillTypes = new ArrayList<>();

		SkillType skillType1 = new SkillType();
		skillType1.setName("Méthodologie");
		skillTypeRepository.save(skillType1);
		skillTypes.add(skillType1);

		SkillType skillType2 = new SkillType();
		skillType2.setName("Technique");
		skillTypeRepository.save(skillType2);
		skillTypes.add(skillType2);

		SkillType skillType3 = new SkillType();
		skillType3.setName("Key");
		skillTypeRepository.save(skillType3);
		skillTypes.add(skillType3);
	}

	@Test
	@DisplayName("Chargement d'une type de compétence à partir de son ID")
	void findSkillTypeById_ReturnCorrectSkillType() {
		// Arrange
		SkillType searchSkillType = skillTypes.get(2);

		// Act
		Optional<SkillType> optSkillType = skillTypeRepository.findById(searchSkillType.getId());

		// Assert
		Assertions.assertTrue(optSkillType.isPresent(),
				"Le type de compétence n'a pas été trouvée pour l'ID " + searchSkillType.getId());
		SkillType skillType = optSkillType.get();
		Assertions.assertNotNull(skillType);
		Assertions.assertEquals(searchSkillType.getId(), skillType.getId());
		Assertions.assertEquals(searchSkillType.getName(), skillType.getName());
	}

	@Test
	@DisplayName("Chargement d'une type de compétence à partir d'un ID inexistant")
	void findSkillTypeById_ReturnNullWhenNotFound() {
		// Arrange
		Integer id = 999;

		// Act
		Optional<SkillType> optSkillType = skillTypeRepository.findById(id);

		// Assert
		Assertions.assertFalse(optSkillType.isPresent(), "Le type de compétence a été trouvée pour l'ID " + id);
	}
}

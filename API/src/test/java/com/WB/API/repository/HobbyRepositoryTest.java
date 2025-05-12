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

import com.WB.API.assertions.HobbyAssertions;
import com.WB.API.model.Hobby;

/*
 * Tests du repository des passions
 * Lors de ces test, nous souhaitons vérifier le fonctionnement du repository 
 * Ces tests seront effectué sur une base de test H2
 */
@DataJpaTest
@ActiveProfiles("test")
@DisplayName("Test du repository des passions")
class HobbyRepositoryTest {

	@Autowired
	private HobbyRepository hobbyRepository;

	private List<Hobby> hobbies;

	/**
	 * Chargement des données de tests spécifique au repository
	 */
	@BeforeEach
	private void loadData() {
		hobbies = new ArrayList<>();

		Hobby hobby1 = new Hobby();
		hobby1.setName("Modélisme");
		hobbyRepository.save(hobby1);
		hobbies.add(hobby1);

		Hobby hobby2 = new Hobby();
		hobby2.setName("Collectionneur de BD");
		hobbyRepository.save(hobby2);
		hobbies.add(hobby2);

		Hobby hobby3 = new Hobby();
		hobby3.setName("Bricolage");
		hobbyRepository.save(hobby3);
		hobbies.add(hobby3);
	}

	@Test
	@DisplayName("Chargement d'une passion à partir de son ID")
	void findHobbyById_ReturnCorrectHobby() {
		// Arrange
		Hobby searchHobby = hobbies.get(2);

		// Act
		Optional<Hobby> optHobby = hobbyRepository.findById(searchHobby.getId());

		// Assert
		Assertions.assertTrue(optHobby.isPresent(), "La passion n'a pas été trouvée pour l'ID " + searchHobby.getId());
		Hobby foundHobby = optHobby.get();

		HobbyAssertions.assertEqualsProperties(searchHobby, foundHobby);
	}

	@Test
	@DisplayName("Chargement d'une passion à partir d'un ID inexistant")
	void findHobbyById_ReturnNUllWhenNotFound() {
		// Arrange
		Integer id = 999;

		// Act
		Optional<Hobby> optHobby = hobbyRepository.findById(id);

		// Assert
		Assertions.assertFalse(optHobby.isPresent(), "La passion a été trouvée pour l'ID " + id);
	}
}

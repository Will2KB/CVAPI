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

import com.WB.API.model.Establishment;

/*
 * Tests du repository des établissements
 * Lors de ces test, nous souhaitons vérifier le fonctionnement du repository 
 * Ces tests seront effectué sur une base de test H2
 */
@ActiveProfiles("test")
@DataJpaTest
@DisplayName("Test du repository des établissements")
class EstablishementRepositoryTests {

	@Autowired
	EstablishementRepository establishementRepository;

	private List<Establishment> establishements;

	/**
	 * Chargement des données de tests spécifique au repository
	 */
	@BeforeEach
	private void loadData() {
		establishements = new ArrayList<>();

		Establishment establishement1 = new Establishment();
		establishement1.setName("Université");
		establishementRepository.save(establishement1);
		establishements.add(establishement1);

		Establishment establishement2 = new Establishment();
		establishement2.setName("Company");
		establishementRepository.save(establishement2);
		establishements.add(establishement2);

	}

	@Test
	@DisplayName("Chargement d'un établissement à partir de son ID")
	void findEstablishementById_ReturnCorrectEstablishement() {
		// Arrange
		Establishment searchEstablishement = establishements.get(1);

		// Act
		Optional<Establishment> optEstablishement = establishementRepository.findById(searchEstablishement.getId());

		// Assert
		Assertions.assertTrue(optEstablishement.isPresent(),
				"L'établissement n'a pas été trouvée pour l'ID " + searchEstablishement.getId());
		Establishment establishement = optEstablishement.get();

		Assertions.assertNotNull(establishement);
		Assertions.assertEquals(searchEstablishement.getId(), establishement.getId());
		Assertions.assertEquals(searchEstablishement.getName(), establishement.getName());
	}

	@Test
	@DisplayName("Chargement d'un établissement à partir d'un ID inexistant")
	void findEstablishementById_ReturnNullWhenNotFound() {
		// Arrange
		Integer id = 999;

		// Act
		Optional<Establishment> optEstablishement = establishementRepository.findById(id);

		// Assert
		Assertions.assertFalse(optEstablishement.isPresent(), "L'établissement a été trouvée pour l'ID " + id);
	}
}

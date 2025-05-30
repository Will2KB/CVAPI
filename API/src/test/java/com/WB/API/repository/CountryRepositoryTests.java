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

import com.WB.API.assertions.CountryAssertions;
import com.WB.API.model.Country;

/*
 * Tests du repository des pays
 * Lors de ces test, nous souhaitons vérifier le fonctionnement du repository 
 * Ces tests seront effectué sur une base de test H2
 */
@DataJpaTest
@ActiveProfiles("test")
@DisplayName("Test du repository des pays")
class CountryRepositoryTests {

	@Autowired
	private CountryRepository countryRepository;

	private List<Country> countries;

	/**
	 * Chargement des données de tests spécifique au repository
	 */
	@BeforeEach
	private void loadData() {
		countries = new ArrayList<>();

		Country country1 = new Country("France");
		countryRepository.save(country1);
		countries.add(country1);

		Country country2 = new Country("Suisse");
		countryRepository.save(country2);
		countries.add(country2);

		Country country3 = new Country("Espagne");
		countryRepository.save(country3);
		countries.add(country3);

		Country country4 = new Country("Maroc");
		countryRepository.save(country4);
		countries.add(country4);
	}

	@Test
	@DisplayName("Chargement de tous les pays")
	void findAllCountries_ReturnCorrectList() {
		// Arrange @BeforeEach

		// Act
		List<Country> findCountry = countryRepository.findAll();

		// Assert
		CountryAssertions.assertListEntities(findCountry, countries);
	}

	@Test
	@DisplayName("Chargement d'un pays à partir de son ID")
	void findCountryById_ReturnCorrectCountry() {
		// Arrange
		Country searchCountry = countries.get(2);

		// Act
		Optional<Country> optCountry = countryRepository.findById(searchCountry.getId());

		// Assert
		Assertions.assertTrue(optCountry.isPresent(), "Le pays n'a pas été trouvée pour l'ID " + searchCountry.getId());
		Country country = optCountry.get();

		CountryAssertions.assertNotNullEntity(country);
		CountryAssertions.assertEqualsProperties(searchCountry, country);
	}

	@Test
	@DisplayName("Chargement d'un pays à partir d'un ID inexistant")
	void findCountryById_ReturnNullWhenNotFound() {
		// Arrange
		Integer id = 999;

		// Act
		Optional<Country> optCountry = countryRepository.findById(id);

		// Assert
		Assertions.assertFalse(optCountry.isPresent(), "Le pays a été trouvée pour l'ID " + id);
	}

}

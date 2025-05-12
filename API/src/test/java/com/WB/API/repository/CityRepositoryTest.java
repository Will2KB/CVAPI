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

import com.WB.API.assertions.CityAssertions;
import com.WB.API.model.City;

/*
 * Tests du repository des villes
 * Lors de ces test, nous souhaitons vérifier le fonctionnement du repository 
 * Ces tests seront effectué sur une base de test H2
 */
@ActiveProfiles("test")
@DataJpaTest
@DisplayName("Test du repository des villes")
class CityRepositoryTest {

	@Autowired
	private CityRepository cityRepository;

	private List<City> cities;

	/**
	 * Chargement des données de tests spécifique au repository
	 */
	@BeforeEach
	void loadData() {

		cities = new ArrayList<>();

		City city1 = new City("Bonneville", 74130);
		cityRepository.save(city1);
		cities.add(city1);

		City city2 = new City("Genève", 1201);
		cityRepository.save(city2);
		cities.add(city2);

		City city3 = new City("Plan-les-Ouates", 1212);
		cityRepository.save(city3);
		cities.add(city3);

		City city4 = new City("Paris", 75000);
		cityRepository.save(city4);
		cities.add(city4);
	}

	@Test
	@DisplayName("Chargement de toutes les villes")
	void findAllCities_ReturnCorrectList() {
		// Arrange @BeforEach

		// Act
		List<City> findCities = cityRepository.findAll();

		// Assert
		for (City expectedCity : cities) {
			City actualCity = findCities.stream().filter(c -> c.getName().equals(expectedCity.getName())).findFirst()
					.orElse(null);

			CityAssertions.assertEqualsProperties(expectedCity, actualCity);
		}
	}

	@Test
	@DisplayName("Chargement d'une ville à partir de son nom")
	void findCityByName_ReturnCorrectCity() {
		// Arrange
		City searchCity = cities.get(2);

		// Act
		City findCity = cityRepository.findFirstCityByName(searchCity.getName());

		// Assert
		CityAssertions.assertEqualsProperties(searchCity, findCity);
	}

	@Test
	@DisplayName("Chargement d'une ville à partir d'un nom inexistant")
	void findCityByName_ReturnNullWhenNotFoud() {
		// Arrange
		String name = "NotFound";

		// Act
		City findCity = cityRepository.findFirstCityByName(name);

		// Assert
		Assertions.assertNull(findCity);
	}

	@Test
	@DisplayName("Chargement d'une ville à partir de son nom alors qu'il existe 2 villes en base avec le même nom")
	void findCityByName_ReturnFirstValue() {
		// Arrange
		City searchCity = cities.get(2);
		City newCity = new City(searchCity.getName(), searchCity.getZipCode());

		// Act
		cityRepository.save(newCity);

		// Assert
		City findCity = cityRepository.findFirstCityByName(searchCity.getName());
		CityAssertions.assertEqualsProperties(searchCity, findCity);

	}

	@Test
	@DisplayName("Chargement d'une ville à partir de son ID")
	void findCityById_ReturnCorrectCity() {
		// Arrange
		City searchCity = cities.get(2);

		// Act
		Optional<City> optCity = cityRepository.findById(searchCity.getId());

		// Assert
		Assertions.assertTrue(optCity.isPresent(), "La ville n'a pas été trouvée pour l'ID " + searchCity.getId());
		City city = optCity.get();
		CityAssertions.assertEqualsProperties(searchCity, city);
	}

	@Test
	@DisplayName("Chargement d'une ville à partir d'un ID inexistant")
	void findCityById_ReturnNullWhenNotFound() {
		// Arrange
		Integer id = 999;

		// Act
		Optional<City> optCity = cityRepository.findById(id);

		// Assert
		Assertions.assertFalse(optCity.isPresent(), "La ville a été trouvée pour l'ID " + id);
	}
}

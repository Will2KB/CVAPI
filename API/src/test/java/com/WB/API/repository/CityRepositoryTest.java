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

import com.WB.API.model.City;

@ActiveProfiles("test")
@DataJpaTest
@DisplayName("Test du repository des villes")
class CityRepositoryTest {

	@Autowired
	private CityRepository cityRepository;

	private List<City> cities;

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
		List<City> findCities = cityRepository.findAll();

		for (City expectedCity : cities) {
			City actualCity = findCities.stream().filter(c -> c.getName().equals(expectedCity.getName())).findFirst()
					.orElse(null);

			Assertions.assertNotNull(actualCity);
			Assertions.assertEquals(expectedCity.getId(), actualCity.getId());
			Assertions.assertEquals(expectedCity.getZip_code(), actualCity.getZip_code());
		}
	}

	@Test
	@DisplayName("Chargement d'une ville à partir de son nom")
	void findCityByName_ReturnCorrectCity() {
		City searchCity = cities.get(2);
		City findCity = cityRepository.findFirstCityByName(searchCity.getName());
		Assertions.assertNotNull(findCity);
		Assertions.assertEquals(searchCity.getName(), findCity.getName());
	}

	@Test
	@DisplayName("Chargement d'une ville à partir de son nom alors qu'il existe 2 villes en base avec le même nom")
	void findCityByName_ReturnFirstValue() {
		City searchCity = cities.get(2);
		City newCity = new City(searchCity.getName(), searchCity.getZip_code());
		cityRepository.save(newCity);

		City findCity = cityRepository.findFirstCityByName(searchCity.getName());
		Assertions.assertNotNull(findCity);
		Assertions.assertEquals(searchCity.getName(), findCity.getName());
	}

	@Test
	@DisplayName("Chargement d'une ville à partir de son ID")
	void findCityById_ReturnCorrectCity() {
		City searchCity = cities.get(2);
		Optional<City> optCity = cityRepository.findById(searchCity.getId());

		Assertions.assertTrue(optCity.isPresent(), "La ville n'a pas été trouvée pour l'ID " + searchCity.getId());
		City city = optCity.get();

		Assertions.assertNotNull(city);
		Assertions.assertEquals(searchCity.getId(), city.getId());
		Assertions.assertEquals(searchCity.getName(), city.getName());
		Assertions.assertEquals(searchCity.getZip_code(), city.getZip_code());
	}

}

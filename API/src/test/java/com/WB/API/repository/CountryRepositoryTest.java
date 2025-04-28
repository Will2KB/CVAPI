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

import com.WB.API.model.Country;

@DataJpaTest
@ActiveProfiles("test")
@DisplayName("Test du repository des pays")
class CountryRepositoryTest {

	@Autowired
	private CountryRepository countryRepository;

	private List<Country> countries;

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
		List<Country> findCountry = countryRepository.findAll();

		for (Country expectedCountry : countries) {
			Country actualCountry = findCountry.stream().filter(c -> c.getName().equals(expectedCountry.getName()))
					.findFirst().orElse(null);

			Assertions.assertNotNull(actualCountry);
			Assertions.assertEquals(expectedCountry.getId(), actualCountry.getId());
			Assertions.assertEquals(expectedCountry.getName(), actualCountry.getName());
		}
	}

	@Test
	@DisplayName("Chargement d'un pays à partir de son ID")
	void findCountryById_ReturnCorrectCountry() {
		Country searchCountry = countries.get(2);
		Optional<Country> optCountry = countryRepository.findById(searchCountry.getId());

		Assertions.assertTrue(optCountry.isPresent(), "Le pays n'a pas été trouvée pour l'ID " + searchCountry.getId());
		Country country = optCountry.get();

		Assertions.assertNotNull(country);
		Assertions.assertEquals(searchCountry.getId(), country.getId());
		Assertions.assertEquals(searchCountry.getName(), country.getName());
	}

}

package com.WB.API.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import com.WB.API.model.Country;
import com.WB.API.repository.CountryRepository;

@ActiveProfiles("test")
@DisplayName("Test du service des pays")
@ExtendWith(MockitoExtension.class)
class CountryServiceTest {

	@Mock
	private CountryRepository CountryRepository;

	@InjectMocks
	private CountryService countryService;

	private List<Country> mockedListCountries;

	@BeforeEach
	private void LoadData() {

		mockedListCountries = new ArrayList<Country>();
		mockedListCountries.add(new Country(12, "France"));
		mockedListCountries.add(new Country(13, "Suisse"));
		mockedListCountries.add(new Country(14, "Espagne"));

	}

	@Test
	@DisplayName("Recherche d'une pays à partir de l'ID")
	void getCountryById_ShouldReturnCorrectCountry() {
		Country mockedCountry = mockedListCountries.get(2);
		Mockito.when(CountryRepository.findById(mockedCountry.getId())).thenReturn(Optional.of(mockedCountry));
		Country result = countryService.getContryById(mockedCountry.getId());

		Assertions.assertNotNull(result);
		Assertions.assertEquals(mockedCountry.getName(), result.getName());
	}

	@Test
	@DisplayName("Chargement de toutes les Countrynes")
	void findAll_ShouldReturnListOfAllCountrys() {
		Mockito.when(CountryRepository.findAll()).thenReturn(mockedListCountries);

		List<Country> loadedCountrys = countryService.getCountries();

		for (Country expectedCountry : mockedListCountries) {
			Country actualCountry = loadedCountrys.stream().filter(c -> c.getName().equals(expectedCountry.getName()))
					.findFirst().orElse(null);

			Assertions.assertNotNull(actualCountry);
			Assertions.assertEquals(expectedCountry.getId(), actualCountry.getId());
			Assertions.assertEquals(expectedCountry.getName(), actualCountry.getName());
		}
	}
}
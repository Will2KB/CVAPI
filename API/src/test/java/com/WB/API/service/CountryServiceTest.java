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

import com.WB.API.dto.CountryDTO;
import com.WB.API.mapper.CountryMapper;
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

	private List<CountryDTO> mockedListCountries;

	@BeforeEach
	private void LoadData() {

		mockedListCountries = new ArrayList<>();
		mockedListCountries.add(new CountryDTO(12, "France"));
		mockedListCountries.add(new CountryDTO(13, "Suisse"));
		mockedListCountries.add(new CountryDTO(14, "Espagne"));

	}

	private List<Country> getMockedListEntity() {
		List<Country> countries = new ArrayList<>();

		for (CountryDTO countryDTO : mockedListCountries) {
			countries.add(CountryMapper.toEntity(countryDTO));
		}

		return countries;
	}

	@Test
	@DisplayName("Recherche d'une pays à partir de l'ID")
	void getCountryById_ShouldReturnCorrectCountry() {
		CountryDTO mockedCountryDTO = mockedListCountries.get(2);
		Country mockedCountry = new Country(mockedCountryDTO.getId(), mockedCountryDTO.getName());
		Mockito.when(CountryRepository.findById(mockedCountryDTO.getId())).thenReturn(Optional.of(mockedCountry));
		CountryDTO result = countryService.getContryById(mockedCountryDTO.getId());

		Assertions.assertNotNull(result);
		Assertions.assertEquals(mockedCountryDTO.getName(), result.getName());
	}

	@Test
	@DisplayName("Chargement de toutes les Countrynes")
	void findAll_ShouldReturnListOfAllCountrys() {
		Mockito.when(CountryRepository.findAll()).thenReturn(this.getMockedListEntity());

		List<CountryDTO> loadedCountrys = countryService.getCountries();

		for (CountryDTO expectedCountry : mockedListCountries) {
			CountryDTO actualCountry = loadedCountrys.stream()
					.filter(c -> c.getName().equals(expectedCountry.getName())).findFirst().orElse(null);

			Assertions.assertNotNull(actualCountry);
			Assertions.assertEquals(expectedCountry.getId(), actualCountry.getId());
			Assertions.assertEquals(expectedCountry.getName(), actualCountry.getName());
		}
	}
}
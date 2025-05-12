package com.WB.API.service;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import com.WB.API.assertions.CountryAssertions;
import com.WB.API.assertions.TestDatas;
import com.WB.API.dto.CountryDTO;
import com.WB.API.exceptions.RessourceNotFoundException;
import com.WB.API.model.Country;
import com.WB.API.repository.CountryRepository;

/*
 * Tests du service des pays
 * Lors de ces test, nous souhaitons vérifier le fonctionnement du service 
 * c'est pourquoi le comportement du repository (auquel le service fait appel) sera mocké
 */
@ActiveProfiles("test")
@DisplayName("Test du service des pays")
@ExtendWith(MockitoExtension.class)
class CountryServiceTests {

	@Mock
	private CountryRepository CountryRepository;

	@InjectMocks
	private CountryService countryService;

	@Test
	@DisplayName("Recherche d'une pays à partir de l'ID")
	void getCountryById_ShouldReturnCorrectCountry() {
		// Arrange
		CountryDTO mockedCountryDTO = CountryAssertions.getCountryDTO();
		Country mockedCountry = new Country(mockedCountryDTO.getId(), mockedCountryDTO.getName());
		Mockito.when(CountryRepository.findById(mockedCountryDTO.getId())).thenReturn(Optional.of(mockedCountry));

		// Act
		CountryDTO result = countryService.getContryById(mockedCountryDTO.getId());

		// Assert
		CountryAssertions.assertNotNullDTO(result);
		CountryAssertions.assertEqualsProperties(mockedCountry, result);
	}

	@Test
	@DisplayName("Recherche d'une pays à partir d'un ID inexistant")
	void getCountryById_ShouldReturnNullWhenNotFound() {
		// Arrange
		Integer id = 999;
		Mockito.when(CountryRepository.findById(id)).thenReturn(Optional.empty());

		// Act + Assert
		RessourceNotFoundException exception = Assertions.assertThrows(RessourceNotFoundException.class,
				() -> countryService.getContryById(id));

		// Vérification du message de l'exception
		Assertions.assertEquals("Country not found with id: " + id, exception.getMessage());
	}

	@Test
	@DisplayName("Chargement de toutes les pays")
	void findAll_ShouldReturnListOfAllCountrys() {
		// Arrange
		TestDatas<Country, CountryDTO> datas = CountryAssertions.getCountryTestDatas(6);
		Mockito.when(CountryRepository.findAll()).thenReturn(datas.entities);

		// Act
		List<CountryDTO> loadedCountrys = countryService.getCountries();

		// Assert
		CountryAssertions.assertListEntitiesDTOs(datas.entities, loadedCountrys);
	}

	@Test
	@DisplayName("Chargement d'une liste vide si la base de données est vide")
	void findAll_ShouldReturnEmptyListWhenBDDEmpty() {
		// Arrange
		Mockito.when(CountryRepository.findAll()).thenReturn(List.of());

		// Act + Assert
		RessourceNotFoundException exception = Assertions.assertThrows(RessourceNotFoundException.class,
				() -> countryService.getCountries());

		// Vérification du message de l'exception
		Assertions.assertEquals("List of countries is empty.", exception.getMessage());
	}
}
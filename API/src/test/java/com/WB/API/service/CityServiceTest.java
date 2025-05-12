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

import com.WB.API.assertions.CityAssertions;
import com.WB.API.assertions.TestDatas;
import com.WB.API.dto.CityDTO;
import com.WB.API.exceptions.RessourceNotFoundException;
import com.WB.API.model.City;
import com.WB.API.repository.CityRepository;

/*
 * Tests du service des villes
 * Lors de ces test, nous souhaitons vérifier le fonctionnement du service 
 * c'est pourquoi le comportement du repository (auquel le service fait appel) sera mocké
 */
@ActiveProfiles("test")
@DisplayName("Test du service des villes")
@ExtendWith(MockitoExtension.class)
class CityServiceTest {

	@Mock
	private CityRepository CityRepository;

	@InjectMocks
	private CityService CityService;

	@Test
	@DisplayName("Recherche d'une ville à partir de l'ID")
	void getCityById_ShouldReturnCorrectCity() {
		// Arrange
		CityDTO mockedCityDTO = CityAssertions.getCityDTO();
		City mockedCity = new City(mockedCityDTO.getId(), mockedCityDTO.getName(), mockedCityDTO.getZipCode());
		Mockito.when(CityRepository.findById(mockedCityDTO.getId())).thenReturn(Optional.of(mockedCity));

		// Act
		CityDTO result = CityService.getCityByID(mockedCityDTO.getId());

		// Assert
		CityAssertions.assertNotNullDTO(result);
		CityAssertions.assertEqualsProperties(mockedCityDTO, result);
	}

	@Test
	@DisplayName("Recherche d'une ville à partir d'un ID inexistant")
	void getCityById_ShouldReturnNullWhenNotFound() {
		// Arrange
		Integer id = 999;
		Mockito.when(CityRepository.findById(id)).thenReturn(Optional.empty());

		// Act + Assert
		RessourceNotFoundException exception = Assertions.assertThrows(RessourceNotFoundException.class,
				() -> CityService.getCityByID(id));

		// Vérification du message de l'exception
		Assertions.assertEquals("City not found with id: " + id, exception.getMessage());
	}

	@Test
	@DisplayName("Recherche d'une ville à partir du nom")
	void findByName_ShouldReturnCorrectCity() {
		// Arrange
		CityDTO mockedCityDTO = CityAssertions.getCityDTO();
		City mockedCity = new City(mockedCityDTO.getId(), mockedCityDTO.getName(), mockedCityDTO.getZipCode());
		Mockito.when(CityRepository.findFirstCityByName(mockedCity.getName())).thenReturn(mockedCity);

		// Act
		CityDTO result = CityService.getCityByName(mockedCity.getName());

		// Assert
		Assertions.assertNotNull(result);
		Assertions.assertEquals(mockedCity.getName(), result.getName());
		Assertions.assertEquals(mockedCity.getZipCode(), result.getZipCode());
	}

	@Test
	@DisplayName("Recherche d'une ville à partir d'un nom innexistant")
	void findByName_ShouldReturnNullWhenNotFound() {
		// Arrange
		String name = "Toto";
		Mockito.when(CityRepository.findFirstCityByName(name)).thenReturn(null);

		// Act + Assert
		RessourceNotFoundException exception = Assertions.assertThrows(RessourceNotFoundException.class,
				() -> CityService.getCityByName(name));

		// Vérification du message de l'exception
		Assertions.assertEquals("City not found with name: " + name, exception.getMessage());

	}

	@Test
	@DisplayName("Chargement de toutes les villes")
	void getAllCities_ShouldReturnListOfAllCitys() {
		// Arrange
		TestDatas<City, CityDTO> datas = CityAssertions.getCityTestDatas(8);
		Mockito.when(CityRepository.findAll()).thenReturn(datas.entities);

		// Act
		List<CityDTO> loadedCitys = CityService.getCities();

		// Assert
		CityAssertions.assertListEntitiesDTOs(datas.entities, loadedCitys);
	}

	@Test
	@DisplayName("Chargement de toutes les villes quand la base de données est vide")
	void getAllCities_ShouldReturnEmptyListWhenBDDEmpty() {
		// Arrange
		Mockito.when(CityRepository.findAll()).thenReturn(List.of());

		// Act + Assert
		RessourceNotFoundException exception = Assertions.assertThrows(RessourceNotFoundException.class,
				() -> CityService.getCities());

		// Vérification du message de l'exception
		Assertions.assertEquals("List of cities is empty.", exception.getMessage());

	}
}
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

import com.WB.API.model.City;
import com.WB.API.repository.CityRepository;

@ActiveProfiles("test")
@DisplayName("Test du service des villes")
@ExtendWith(MockitoExtension.class)
class CityServiceTest {

	@Mock
	private CityRepository CityRepository;

	@InjectMocks
	private CityService CityService;

	private List<City> mockedListCities;

	@BeforeEach
	private void LoadData() {
		mockedListCities = new ArrayList<City>();
		mockedListCities.add(new City(12, "Bonneville", 74130));
		mockedListCities.add(new City(13, "Genève", 1201));
		mockedListCities.add(new City(14, "Plan-les-Ouates", 1212));
		mockedListCities.add(new City(15, "Paris", 75000));
	}

	@Test
	@DisplayName("Recherche d'une ville à partir de l'ID")
	void getCityById_ShouldReturnCorrectCity() {
		City mockedCity = mockedListCities.get(2);
		Mockito.when(CityRepository.findById(mockedCity.getId())).thenReturn(Optional.of(mockedCity));
		City result = CityService.getCityByID(mockedCity.getId());

		Assertions.assertNotNull(result);
		Assertions.assertEquals(mockedCity.getName(), result.getName());
		Assertions.assertEquals(mockedCity.getZip_code(), result.getZip_code());
	}

	@Test
	@DisplayName("Recherche d'une ville à partir du nom")
	void findByName_ShouldReturnCorrectCity() {
		City mockedCity = mockedListCities.get(2);
		Mockito.when(CityRepository.findFirstCityByName(mockedCity.getName())).thenReturn(mockedCity);
		City result = CityService.getCityByName(mockedCity.getName());

		Assertions.assertNotNull(result);
		Assertions.assertEquals(mockedCity.getName(), result.getName());
		Assertions.assertEquals(mockedCity.getZip_code(), result.getZip_code());
	}

	@Test
	@DisplayName("Chargement de toutes les villes")
	void getAllCities_ShouldReturnListOfAllCitys() {
		Mockito.when(CityRepository.findAll()).thenReturn(mockedListCities);

		List<City> loadedCitys = CityService.getCities();

		for (City expectedCity : mockedListCities) {
			City actualCity = loadedCitys.stream().filter(c -> c.getName().equals(expectedCity.getName())).findFirst()
					.orElse(null);

			Assertions.assertNotNull(actualCity);
			Assertions.assertEquals(expectedCity.getId(), actualCity.getId());
			Assertions.assertEquals(expectedCity.getName(), actualCity.getName());
			Assertions.assertEquals(expectedCity.getZip_code(), actualCity.getZip_code());
		}
	}
}
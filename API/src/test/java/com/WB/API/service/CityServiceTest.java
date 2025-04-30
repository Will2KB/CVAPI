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

import com.WB.API.dto.CityDTO;
import com.WB.API.mapper.CityMapper;
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

	private List<CityDTO> mockedListCities;

	@BeforeEach
	private void LoadData() {
		mockedListCities = new ArrayList<>();
		mockedListCities.add(new CityDTO(12, "Bonneville", 74130));
		mockedListCities.add(new CityDTO(13, "Genève", 1201));
		mockedListCities.add(new CityDTO(14, "Plan-les-Ouates", 1212));
		mockedListCities.add(new CityDTO(15, "Paris", 75000));
	}

	private List<City> getMockedListEntity() {
		List<City> cities = new ArrayList<>();

		for (CityDTO cityDTO : mockedListCities) {
			cities.add(CityMapper.toEntity(cityDTO));
		}

		return cities;
	}

	@Test
	@DisplayName("Recherche d'une ville à partir de l'ID")
	void getCityById_ShouldReturnCorrectCity() {
		CityDTO mockedCityDTO = mockedListCities.get(2);
		City mockedCity = new City(mockedCityDTO.getId(), mockedCityDTO.getName(), mockedCityDTO.getZipCode());
		Mockito.when(CityRepository.findById(mockedCityDTO.getId())).thenReturn(Optional.of(mockedCity));
		CityDTO result = CityService.getCityByID(mockedCityDTO.getId());

		Assertions.assertNotNull(result);
		Assertions.assertEquals(mockedCityDTO.getName(), result.getName());
		Assertions.assertEquals(mockedCityDTO.getZipCode(), result.getZipCode());
	}

	@Test
	@DisplayName("Recherche d'une ville à partir du nom")
	void findByName_ShouldReturnCorrectCity() {
		CityDTO mockedCityDTO = mockedListCities.get(2);
		City mockedCity = new City(mockedCityDTO.getId(), mockedCityDTO.getName(), mockedCityDTO.getZipCode());
		Mockito.when(CityRepository.findFirstCityByName(mockedCity.getName())).thenReturn(mockedCity);
		CityDTO result = CityService.getCityByName(mockedCity.getName());

		Assertions.assertNotNull(result);
		Assertions.assertEquals(mockedCity.getName(), result.getName());
		Assertions.assertEquals(mockedCity.getZip_code(), result.getZipCode());
	}

	@Test
	@DisplayName("Chargement de toutes les villes")
	void getAllCities_ShouldReturnListOfAllCitys() {
		Mockito.when(CityRepository.findAll()).thenReturn(this.getMockedListEntity());

		List<CityDTO> loadedCitys = CityService.getCities();

		for (CityDTO expectedCity : mockedListCities) {
			CityDTO actualCity = loadedCitys.stream().filter(c -> c.getName().equals(expectedCity.getName()))
					.findFirst().orElse(null);

			Assertions.assertNotNull(actualCity);
			Assertions.assertEquals(expectedCity.getId(), actualCity.getId());
			Assertions.assertEquals(expectedCity.getName(), actualCity.getName());
			Assertions.assertEquals(expectedCity.getZipCode(), actualCity.getZipCode());
		}
	}
}
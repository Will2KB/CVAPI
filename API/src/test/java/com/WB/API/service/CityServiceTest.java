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

	@Test
	@DisplayName("Recherche d'une ville à partir de l'ID")
	void getCityById_ShouldReturnCorrectCity() {
		CityDTO mockedCityDTO = CityAssertions.getCityDTO();
		City mockedCity = new City(mockedCityDTO.getId(), mockedCityDTO.getName(), mockedCityDTO.getZipCode());
		Mockito.when(CityRepository.findById(mockedCityDTO.getId())).thenReturn(Optional.of(mockedCity));

		CityDTO result = CityService.getCityByID(mockedCityDTO.getId());

		CityAssertions.assertNotNullDTO(result);
		CityAssertions.assertEqualsProperties(mockedCityDTO, result);
	}

	@Test
	@DisplayName("Recherche d'une ville à partir du nom")
	void findByName_ShouldReturnCorrectCity() {
		CityDTO mockedCityDTO = CityAssertions.getCityDTO();
		City mockedCity = new City(mockedCityDTO.getId(), mockedCityDTO.getName(), mockedCityDTO.getZipCode());
		Mockito.when(CityRepository.findFirstCityByName(mockedCity.getName())).thenReturn(mockedCity);
		CityDTO result = CityService.getCityByName(mockedCity.getName());

		Assertions.assertNotNull(result);
		Assertions.assertEquals(mockedCity.getName(), result.getName());
		Assertions.assertEquals(mockedCity.getZipCode(), result.getZipCode());
	}

	@Test
	@DisplayName("Chargement de toutes les villes")
	void getAllCities_ShouldReturnListOfAllCitys() {
		TestDatas<City, CityDTO> datas = CityAssertions.getSkillTestDatas(8);
		Mockito.when(CityRepository.findAll()).thenReturn(datas.entities);

		List<CityDTO> loadedCitys = CityService.getCities();

		CityAssertions.assertListEntitiesDTOs(datas.entities, loadedCitys);
	}
}
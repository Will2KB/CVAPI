package com.WB.API.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content; // Correct import pour vérifier le contenu
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.WB.API.assertions.CityAssertions;
import com.WB.API.assertions.TestDatas;
import com.WB.API.dto.CityDTO;
import com.WB.API.exceptions.RessourceNotFoundException;
import com.WB.API.model.City;
import com.WB.API.service.CityService;

@ActiveProfiles("test")
@DisplayName("Test du controleur des villes")
@WebMvcTest(controllers = CityController.class)
@AutoConfigureMockMvc(addFilters = false)
class CityControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CityService cityService;

	@Test
	@DisplayName("Requête API pour charger toutes les villes")
	void testGetCities_ReturnAllCities() throws Exception {
		// Arrange
		TestDatas<City, CityDTO> datas = CityAssertions.getCityTestDatas(4);
		Mockito.when(cityService.getCities()).thenReturn(datas.dtos);

		// Act + Assert
		mockMvc.perform(get("/cities")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.length()").value(datas.dtos.size()))
				.andExpect(jsonPath("$[0].id").value(datas.dtos.get(0).getId()))
				.andExpect(jsonPath("$[0].name").value(datas.dtos.get(0).getName()))
				.andExpect(jsonPath("$[1].id").value(datas.dtos.get(1).getId()))
				.andExpect(jsonPath("$[1].name").value(datas.dtos.get(1).getName()))
				.andExpect(jsonPath("$[2].id").value(datas.dtos.get(2).getId()))
				.andExpect(jsonPath("$[2].name").value(datas.dtos.get(2).getName()))
				.andExpect(jsonPath("$[3].id").value(datas.dtos.get(3).getId()))
				.andExpect(jsonPath("$[3].name").value(datas.dtos.get(3).getName()));

	}

	@Test
	@DisplayName("Requête API pour charger une liste de villes vide")
	public void testGetCities_ReturnEmpty() throws Exception {
		// Arrange
		when(cityService.getCities()).thenReturn(Collections.emptyList());

		// Act + Assert
		mockMvc.perform(get("/cities")).andExpect(status().isOk()).andExpect(jsonPath("$.size()").value(0));
	}

	@Test
	@DisplayName("Requête API pour charger une ville à partir de son ID")
	void testGetCityById_ReturnCorrectCity() throws Exception {
		// Arrange
		CityDTO searchCity = CityAssertions.getCityDTO();
		Mockito.when(cityService.getCityByID(searchCity.getId())).thenReturn(searchCity);

		// Act + Assert
		mockMvc.perform(get("/cities/id/" + searchCity.getId())).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(searchCity.getId()))
				.andExpect(jsonPath("$.name").value(searchCity.getName()));

	}

	@Test
	@DisplayName("Requête API pour charger une ville à partir d'un ID inexistant")
	public void testGetCityById_NotFound() throws Exception {
		// Arrange
		when(cityService.getCityByID(99)).thenThrow(new RessourceNotFoundException("City not found"));

		// Act + Assert
		mockMvc.perform(get("/cities/id/99")).andExpect(status().isNotFound());
	}

	@Test
	@DisplayName("Requête API pour charger une ville à partir de son nom")
	void testGetCityByName_ReturnCorrectCity() throws Exception {
		// Arrange
		CityDTO searchCity = CityAssertions.getCityDTO();
		Mockito.when(cityService.getCityByName(searchCity.getName())).thenReturn(searchCity);

		// Act + Assert
		mockMvc.perform(get("/cities/name/" + searchCity.getName())).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(searchCity.getId()))
				.andExpect(jsonPath("$.name").value(searchCity.getName()));

	}

	@Test
	@DisplayName("Requête API pour charger une ville à partir d'un nom inexistant")
	public void testGetCityByName_NotFound() throws Exception {
		// Arrange
		when(cityService.getCityByName("NotFound")).thenThrow(new RessourceNotFoundException("City not found"));

		// Act + Assert
		mockMvc.perform(get("/cities/name/NotFound")).andExpect(status().isNotFound());
	}

}
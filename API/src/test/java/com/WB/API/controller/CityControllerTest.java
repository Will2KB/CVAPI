package com.WB.API.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content; // Correct import pour vérifier le contenu
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.WB.API.assertions.CityAssertions;
import com.WB.API.assertions.TestDatas;
import com.WB.API.dto.CityDTO;
import com.WB.API.model.City;
import com.WB.API.service.CityService;

@ActiveProfiles("test")
@DisplayName("Test du controleur des villes")
@WebMvcTest(controllers = CityController.class)
class CityControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CityService cityService;

	@Test
	@DisplayName("Requête API pour charger toutes les villes")
	void testGetCities_ReturnAllCities() throws Exception {
		TestDatas<City, CityDTO> datas = CityAssertions.getSkillTestDatas(4);
		Mockito.when(cityService.getCities()).thenReturn(datas.dtos);

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
	@DisplayName("Requête API pour charger une ville à partir de son ID")
	void testGetCityById_ReturnCorrectCity() throws Exception {
		CityDTO searchCity = CityAssertions.getCityDTO();
		Mockito.when(cityService.getCityByID(searchCity.getId())).thenReturn(searchCity);

		mockMvc.perform(get("/cities/id/" + searchCity.getId())).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(searchCity.getId()))
				.andExpect(jsonPath("$.name").value(searchCity.getName()));

	}

	@Test
	@DisplayName("Requête API pour charger une ville à partir de son nom")
	void testGetCityByName_ReturnCorrectCity() throws Exception {
		CityDTO searchCity = CityAssertions.getCityDTO();
		Mockito.when(cityService.getCityByName(searchCity.getName())).thenReturn(searchCity);

		mockMvc.perform(get("/cities/name/" + searchCity.getName())).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(searchCity.getId()))
				.andExpect(jsonPath("$.name").value(searchCity.getName()));

	}

}
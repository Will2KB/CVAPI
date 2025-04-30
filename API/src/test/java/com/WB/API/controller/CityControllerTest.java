package com.WB.API.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content; // Correct import pour vérifier le contenu
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.WB.API.dto.CityDTO;
import com.WB.API.service.CityService;

@ActiveProfiles("test")
@DisplayName("Test du controleur des villes")
@WebMvcTest(controllers = CityController.class)
class CityControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CityService cityService;

	private List<CityDTO> mockedListCities;

	@BeforeEach
	public void loadData() {
		mockedListCities = new ArrayList<>();
		mockedListCities.add(new CityDTO(12, "Bonneville", 74130));
		mockedListCities.add(new CityDTO(13, "Genève", 1201));
		mockedListCities.add(new CityDTO(14, "Plan-les-Ouates", 1212));
		mockedListCities.add(new CityDTO(15, "Paris", 75000));
	}

	@Test
	@DisplayName("Requête API pour charger toutes les villes")
	void testGetCities_ReturnAllCities() throws Exception {
		Mockito.when(cityService.getCities()).thenReturn(mockedListCities);

		mockMvc.perform(get("/cities")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.length()").value(mockedListCities.size()))
				.andExpect(jsonPath("$[0].id").value(mockedListCities.get(0).getId()))
				.andExpect(jsonPath("$[0].name").value(mockedListCities.get(0).getName()))
				.andExpect(jsonPath("$[1].id").value(mockedListCities.get(1).getId()))
				.andExpect(jsonPath("$[1].name").value(mockedListCities.get(1).getName()))
				.andExpect(jsonPath("$[2].id").value(mockedListCities.get(2).getId()))
				.andExpect(jsonPath("$[2].name").value(mockedListCities.get(2).getName()))
				.andExpect(jsonPath("$[3].id").value(mockedListCities.get(3).getId()))
				.andExpect(jsonPath("$[3].name").value(mockedListCities.get(3).getName()));

	}

	@Test
	@DisplayName("Requête API pour charger une ville à partir de son ID")
	void testGetCityById_ReturnCorrectCity() throws Exception {
		CityDTO searchCity = mockedListCities.get(2);
		Mockito.when(cityService.getCityByID(searchCity.getId())).thenReturn(searchCity);

		mockMvc.perform(get("/cities/id/" + searchCity.getId())).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(searchCity.getId()))
				.andExpect(jsonPath("$.name").value(searchCity.getName()));

	}

	@Test
	@DisplayName("Requête API pour charger une ville à partir de son nom")
	void testGetCityByName_ReturnCorrectCity() throws Exception {
		CityDTO searchCity = mockedListCities.get(2);
		Mockito.when(cityService.getCityByName(searchCity.getName())).thenReturn(searchCity);

		mockMvc.perform(get("/cities/name/" + searchCity.getName())).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(searchCity.getId()))
				.andExpect(jsonPath("$.name").value(searchCity.getName()));

	}

}
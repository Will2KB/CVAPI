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

import com.WB.API.assertions.CountryAssertions;
import com.WB.API.assertions.TestDatas;
import com.WB.API.dto.CountryDTO;
import com.WB.API.model.Country;
import com.WB.API.service.CountryService;

@ActiveProfiles("test")
@DisplayName("Test du controleur des pays")
@WebMvcTest(controllers = CountryController.class)
class CountryControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CountryService countryService;

	@Test
	@DisplayName("Requête API pour charger tous les pays")
	void testGetCountries_ReturnAllTestDatas() throws Exception {
		TestDatas<Country, CountryDTO> datas = CountryAssertions.getSkillTestDatas(3);
		Mockito.when(countryService.getCountries()).thenReturn(datas.dtos);

		mockMvc.perform(get("/countries")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.length()").value(datas.dtos.size()))
				.andExpect(jsonPath("$[0].id").value(datas.dtos.get(0).getId()))
				.andExpect(jsonPath("$[0].name").value(datas.dtos.get(0).getName()))
				.andExpect(jsonPath("$[1].id").value(datas.dtos.get(1).getId()))
				.andExpect(jsonPath("$[1].name").value(datas.dtos.get(1).getName()))
				.andExpect(jsonPath("$[2].id").value(datas.dtos.get(2).getId()))
				.andExpect(jsonPath("$[2].name").value(datas.dtos.get(2).getName()));

	}

	@Test
	@DisplayName("Requête API pour charger un pays à partir de son ID")
	void testGetCountryById_ReturnCorrectCountry() throws Exception {
		CountryDTO searchCountry = CountryAssertions.getCountryDTO();
		Mockito.when(countryService.getContryById(searchCountry.getId())).thenReturn(searchCountry);

		mockMvc.perform(get("/countries/id/" + searchCountry.getId())).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(searchCountry.getId()))
				.andExpect(jsonPath("$.name").value(searchCountry.getName()));

	}

}
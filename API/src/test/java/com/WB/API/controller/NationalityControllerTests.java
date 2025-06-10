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

import com.WB.API.assertions.NationalityAssertions;
import com.WB.API.assertions.TestDatas;
import com.WB.API.dto.NationalityDTO;
import com.WB.API.exceptions.RessourceNotFoundException;
import com.WB.API.model.Nationality;
import com.WB.API.service.NationalityService;

@ActiveProfiles("test")
@DisplayName("Test du controleur des nationalités")
@WebMvcTest(controllers = NationalityController.class)
@AutoConfigureMockMvc(addFilters = false)
class NationalityControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private NationalityService nationalityService;

	@Test
	@DisplayName("Requête API pour charger toutes les nationalités")
	void testGetNationalities_ReturnAllNationalities() throws Exception {
		// Arrange
		TestDatas<Nationality, NationalityDTO> datas = NationalityAssertions.getNationalityTestDatas(3);
		Mockito.when(nationalityService.getNationalities()).thenReturn(datas.dtos);

		// Act + Assert
		mockMvc.perform(get("/nationalities")).andExpect(status().isOk())
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
	@DisplayName("Requête API pour charger une liste de nationalités vide")
	public void testGetNationalities_ReturnEmpty() throws Exception {
		// Arrange
		when(nationalityService.getNationalities()).thenReturn(Collections.emptyList());

		// Act + Assert
		mockMvc.perform(get("/nationalities")).andExpect(status().isOk()).andExpect(jsonPath("$.size()").value(0));
	}

	@Test
	@DisplayName("Requête API pour charger une nationalité à partir de son ID")
	void testGetNationalityById_ReturnCorrectNationality() throws Exception {
		// Arrange
		NationalityDTO searchNationality = NationalityAssertions.getNationalityDTO();
		Mockito.when(nationalityService.getNationalityById(searchNationality.getId())).thenReturn(searchNationality);

		// Act + Assert
		mockMvc.perform(get("/nationalities/id/" + searchNationality.getId())).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(searchNationality.getId()))
				.andExpect(jsonPath("$.name").value(searchNationality.getName()));

	}

	@Test
	@DisplayName("Requête API pour charger une nationalité à partir d'un ID inexistant")
	public void testGetNationalityById_NotFound() throws Exception {
		// Arrange
		when(nationalityService.getNationalityById(99))
				.thenThrow(new RessourceNotFoundException("Nationality not found"));

		// Act + Assert
		mockMvc.perform(get("/nationalities/id/99")).andExpect(status().isNotFound());
	}

}
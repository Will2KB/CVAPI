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

import com.WB.API.model.Nationality;
import com.WB.API.service.NationalityService;

@ActiveProfiles("test")
@DisplayName("Test du controleur des nationalités")
@WebMvcTest(controllers = NationalityController.class)
class NationalityControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private NationalityService nationalityService;

	private List<Nationality> mockedListNationalities;

	@BeforeEach
	public void loadData() {
		mockedListNationalities = new ArrayList<Nationality>();
		mockedListNationalities.add(new Nationality(12, "Français"));
		mockedListNationalities.add(new Nationality(13, "Suisse"));
		mockedListNationalities.add(new Nationality(14, "Espagnol"));
	}

	@Test
	@DisplayName("Requête API pour charger toutes les nationalités")
	void testGetNationalities_ReturnAllNationalities() throws Exception {
		Mockito.when(nationalityService.getNationalities()).thenReturn(mockedListNationalities);

		mockMvc.perform(get("/nationalities")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.length()").value(mockedListNationalities.size()))
				.andExpect(jsonPath("$[0].id").value(mockedListNationalities.get(0).getId()))
				.andExpect(jsonPath("$[0].name").value(mockedListNationalities.get(0).getName()))
				.andExpect(jsonPath("$[1].id").value(mockedListNationalities.get(1).getId()))
				.andExpect(jsonPath("$[1].name").value(mockedListNationalities.get(1).getName()))
				.andExpect(jsonPath("$[2].id").value(mockedListNationalities.get(2).getId()))
				.andExpect(jsonPath("$[2].name").value(mockedListNationalities.get(2).getName()));

	}

	@Test
	@DisplayName("Requête API pour charger une nationalité à partir de son ID")
	void testGetNationalityById_ReturnCorrectNationality() throws Exception {
		Nationality searchNationality = mockedListNationalities.get(2);
		Mockito.when(nationalityService.getNationalityById(searchNationality.getId())).thenReturn(searchNationality);

		mockMvc.perform(get("/nationalities/id/" + searchNationality.getId())).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(searchNationality.getId()))
				.andExpect(jsonPath("$.name").value(searchNationality.getName()));

	}

}
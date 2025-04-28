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

import com.WB.API.model.Language;
import com.WB.API.service.LanguageService;

@ActiveProfiles("test")
@DisplayName("Test du controleur des langues")
@WebMvcTest(controllers = LanguageController.class)
class LanguageControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private LanguageService languageService;

	private List<Language> mockedListLanguages;

	@BeforeEach
	public void loadData() {
		mockedListLanguages = new ArrayList<Language>();
		mockedListLanguages.add(new Language(12, "Français"));
		mockedListLanguages.add(new Language(13, "Anglais"));
		mockedListLanguages.add(new Language(14, "Espagnol"));
	}

	@Test
	@DisplayName("Requête API pour charger toutes les langues")
	void testGetLanguages_ReturnAllLanguages() throws Exception {
		Mockito.when(languageService.getLanguages()).thenReturn(mockedListLanguages);

		mockMvc.perform(get("/languages")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.length()").value(mockedListLanguages.size()))
				.andExpect(jsonPath("$[0].id").value(mockedListLanguages.get(0).getId()))
				.andExpect(jsonPath("$[0].name").value(mockedListLanguages.get(0).getName()))
				.andExpect(jsonPath("$[1].id").value(mockedListLanguages.get(1).getId()))
				.andExpect(jsonPath("$[1].name").value(mockedListLanguages.get(1).getName()))
				.andExpect(jsonPath("$[2].id").value(mockedListLanguages.get(2).getId()))
				.andExpect(jsonPath("$[2].name").value(mockedListLanguages.get(2).getName()));

	}

}
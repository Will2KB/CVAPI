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

import com.WB.API.assertions.LanguageAssertions;
import com.WB.API.assertions.TestDatas;
import com.WB.API.dto.LanguageDTO;
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

	@Test
	@DisplayName("Requête API pour charger toutes les langues")
	void testGetLanguages_ReturnAllLanguages() throws Exception {
		// Arrange
		TestDatas<Language, LanguageDTO> datas = LanguageAssertions.getLanguageTestDatas(3);
		Mockito.when(languageService.getLanguages()).thenReturn(datas.dtos);

		// Act + Assert
		mockMvc.perform(get("/languages")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.length()").value(datas.dtos.size()))
				.andExpect(jsonPath("$[0].id").value(datas.dtos.get(0).getId()))
				.andExpect(jsonPath("$[0].name").value(datas.dtos.get(0).getName()))
				.andExpect(jsonPath("$[1].id").value(datas.dtos.get(1).getId()))
				.andExpect(jsonPath("$[1].name").value(datas.dtos.get(1).getName()))
				.andExpect(jsonPath("$[2].id").value(datas.dtos.get(2).getId()))
				.andExpect(jsonPath("$[2].name").value(datas.dtos.get(2).getName()));

	}

}
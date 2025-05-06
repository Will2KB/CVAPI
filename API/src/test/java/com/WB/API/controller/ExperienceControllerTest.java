package com.WB.API.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content; // Correct import pour vérifier le contenu
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Collections;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.WB.API.assertions.ExperienceAssertions;
import com.WB.API.assertions.TestSummaryDatas;
import com.WB.API.dto.ExperienceDTO;
import com.WB.API.dto.ExperienceSummaryDTO;
import com.WB.API.model.Experience;
import com.WB.API.service.ExperienceService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ActiveProfiles("test")
@WebMvcTest(controllers = ExperienceController.class)
@DisplayName("Test du controleur d'expériences")
class ExperienceControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private ExperienceService experienceService;

	@Test
	@DisplayName("Requête API pour charger toutes les Experiencenes")
	void testGetExperiences_ReturnAllExperiences() throws Exception {
		TestSummaryDatas<Experience, ExperienceDTO, ExperienceSummaryDTO> datas = ExperienceAssertions
				.getSkillTestDatas(5);
		Mockito.when(experienceService.getExperiences()).thenReturn(datas.dtoSummarries);

		mockMvc.perform(get("/experiences")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.length()").value(datas.dtoSummarries.size()))

				.andExpect(jsonPath("$[0].id").value(datas.dtoSummarries.get(0).getId()))
				.andExpect(jsonPath("$[0].name").value(datas.dtoSummarries.get(0).getName()))
				.andExpect(
						jsonPath("$[0].dateBeginning").value(datas.dtoSummarries.get(0).getDateBeginning().toString()))
				.andExpect(jsonPath("$[0].dateEnding").value(datas.dtoSummarries.get(0).getDateEnding().toString()))
				.andExpect(jsonPath("$[0].formation").value(datas.dtoSummarries.get(0).isFormation()))
				.andExpect(jsonPath("$[1].id").value(datas.dtoSummarries.get(1).getId()))
				.andExpect(jsonPath("$[1].name").value(datas.dtoSummarries.get(1).getName()))
				.andExpect(jsonPath("$[1].id").value(datas.dtoSummarries.get(1).getId()))
				.andExpect(jsonPath("$[1].name").value(datas.dtoSummarries.get(1).getName()))
				.andExpect(
						jsonPath("$[1].dateBeginning").value(datas.dtoSummarries.get(1).getDateBeginning().toString()))
				.andExpect(jsonPath("$[1].dateEnding").value(datas.dtoSummarries.get(1).getDateEnding().toString()))
				.andExpect(jsonPath("$[1].formation").value(datas.dtoSummarries.get(1).isFormation()))
				.andExpect(jsonPath("$[2].id").value(datas.dtoSummarries.get(2).getId()))
				.andExpect(jsonPath("$[2].name").value(datas.dtoSummarries.get(2).getName()))
				.andExpect(jsonPath("$[2].id").value(datas.dtoSummarries.get(2).getId()))
				.andExpect(jsonPath("$[2].name").value(datas.dtoSummarries.get(2).getName()))
				.andExpect(
						jsonPath("$[2].dateBeginning").value(datas.dtoSummarries.get(2).getDateBeginning().toString()))
				.andExpect(jsonPath("$[2].dateEnding").value(datas.dtoSummarries.get(2).getDateEnding().toString()))
				.andExpect(jsonPath("$[2].formation").value(datas.dtoSummarries.get(2).isFormation()))
				.andExpect(jsonPath("$[3].id").value(datas.dtoSummarries.get(3).getId()))
				.andExpect(jsonPath("$[3].name").value(datas.dtoSummarries.get(3).getName()))
				.andExpect(jsonPath("$[3].id").value(datas.dtoSummarries.get(3).getId()))
				.andExpect(jsonPath("$[3].name").value(datas.dtoSummarries.get(3).getName()))
				.andExpect(
						jsonPath("$[3].dateBeginning").value(datas.dtoSummarries.get(3).getDateBeginning().toString()))
				.andExpect(jsonPath("$[3].dateEnding").value(datas.dtoSummarries.get(3).getDateEnding().toString()))
				.andExpect(jsonPath("$[3].formation").value(datas.dtoSummarries.get(3).isFormation()))
				.andExpect(jsonPath("$[4].id").value(datas.dtoSummarries.get(4).getId()))
				.andExpect(jsonPath("$[4].name").value(datas.dtoSummarries.get(4).getName()))
				.andExpect(jsonPath("$[4].id").value(datas.dtoSummarries.get(4).getId()))
				.andExpect(jsonPath("$[4].name").value(datas.dtoSummarries.get(4).getName()))
				.andExpect(
						jsonPath("$[4].dateBeginning").value(datas.dtoSummarries.get(4).getDateBeginning().toString()))
				.andExpect(jsonPath("$[4].dateEnding").value(datas.dtoSummarries.get(4).getDateEnding().toString()))
				.andExpect(jsonPath("$[4].formation").value(datas.dtoSummarries.get(4).isFormation()));

	}

	@Test
	@DisplayName("Requête API pour charger une liste d'expériences vides")
	public void testGetExperiences_ReturnEmpty() throws Exception {
		when(experienceService.getExperiences()).thenReturn(Collections.emptyList());

		mockMvc.perform(get("/experiences")).andExpect(status().isOk()).andExpect(jsonPath("$.size()").value(0));
	}

	@Test
	@DisplayName("Requête API pour charger une Experiencene à partir de son ID")
	void testGetExperienceById_ReturnCorrectExperience() throws Exception {
		ExperienceDTO searchExperience = ExperienceAssertions.getExperienceDTO();
		Mockito.when(experienceService.getExperienceById(searchExperience.getId())).thenReturn(searchExperience);

		mockMvc.perform(get("/experiences/id/" + searchExperience.getId())).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(searchExperience.getId()))
				.andExpect(jsonPath("$.name").value(searchExperience.getName()))
				.andExpect(jsonPath("$.dateBeginning").value(searchExperience.getDateBeginning().toString()))
				.andExpect(jsonPath("$.dateEnding").value(searchExperience.getDateEnding().toString()))
				.andExpect(jsonPath("$.formation").value(searchExperience.isFormation()));

	}

	@Test
	@DisplayName("Requête API pour charger une Experiencene à partir d'un ID inexistant")
	public void testGetExperienceById_NotFound() throws Exception {
		when(experienceService.getExperienceById(99)).thenReturn(null);

		mockMvc.perform(get("/experiences/id/99")).andExpect(status().isNotFound());
	}

	@Test
	@DisplayName("Sauvegarde d'une expérience bien définie")
	public void testSaveExperience_resturnSavedExperience() throws Exception {

		ExperienceDTO newExperience = ExperienceAssertions.getExperienceDTO();
		when(experienceService.saveExperience(Mockito.any(ExperienceDTO.class))).thenReturn(newExperience.getSummary());

		mockMvc.perform(post("/experiences").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(newExperience))).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(newExperience.getId()))
				.andExpect(jsonPath("$.name").value(newExperience.getName()))
				.andExpect(jsonPath("$.dateBeginning").value(newExperience.getDateBeginning().toString()))
				.andExpect(jsonPath("$.dateEnding").value(newExperience.getDateEnding().toString()))
				.andExpect(jsonPath("$.formation").value(newExperience.isFormation()));
	}

	@Test
	@DisplayName("Sauvegarde d'une expérience avec un nom vide")
	public void testSaveExperienceWithEmptyName_StatusFail() throws Exception {
		ExperienceDTO invalidExperience = new ExperienceDTO(12, " ", LocalDate.of(2012, 12, 15),
				LocalDate.of(2016, 2, 5), false);
		when(experienceService.saveExperience(Mockito.any(ExperienceDTO.class)))
				.thenReturn(invalidExperience.getSummary());

		mockMvc.perform(post("/experiences").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(invalidExperience))).andExpect(status().isBadRequest());
	}

	@Test
	@DisplayName("Sauvegarde d'une expérience avec une date de début null")
	public void testSaveExperienceWithDateBeginningNull_StatusFail() throws Exception {
		ExperienceDTO invalidExperience = new ExperienceDTO(12, "Exp. 2", null, LocalDate.of(2016, 2, 5), false);
		when(experienceService.saveExperience(Mockito.any(ExperienceDTO.class)))
				.thenReturn(invalidExperience.getSummary());

		mockMvc.perform(post("/experiences").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(invalidExperience))).andExpect(status().isBadRequest());
	}

}
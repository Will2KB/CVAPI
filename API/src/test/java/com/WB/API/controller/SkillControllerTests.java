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
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.WB.API.assertions.SkillAssertions;
import com.WB.API.assertions.TestDatas;
import com.WB.API.dto.SkillDTO;
import com.WB.API.exceptions.RessourceNotFoundException;
import com.WB.API.model.Skill;
import com.WB.API.service.SkillService;

@ActiveProfiles("test")
@DisplayName("Test du controleur des compétences")
@WebMvcTest(controllers = SkillController.class)
class SkillControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SkillService skillService;

	@Test
	@DisplayName("Requête API pour charger toutes les compétences")
	void testGetSkills_ReturnAllSkills() throws Exception {
		// Arrange
		TestDatas<Skill, SkillDTO> datas = SkillAssertions.getSkillTestDatas(3);
		Mockito.when(skillService.getSkills()).thenReturn(datas.dtos);

		// Act + Assert
		mockMvc.perform(get("/skills")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.length()").value(datas.dtos.size()))
				.andExpect(jsonPath("$[0].id").value(datas.dtos.get(0).getId()))
				.andExpect(jsonPath("$[0].name").value(datas.dtos.get(0).getName()))
				.andExpect(jsonPath("$[0].enable").value(datas.dtos.get(0).isEnable()))
				.andExpect(jsonPath("$[1].id").value(datas.dtos.get(1).getId()))
				.andExpect(jsonPath("$[1].name").value(datas.dtos.get(1).getName()))
				.andExpect(jsonPath("$[1].enable").value(datas.dtos.get(1).isEnable()))
				.andExpect(jsonPath("$[2].id").value(datas.dtos.get(2).getId()))
				.andExpect(jsonPath("$[2].name").value(datas.dtos.get(2).getName()))
				.andExpect(jsonPath("$[2].enable").value(datas.dtos.get(2).isEnable()));

	}

	@Test
	@DisplayName("Requête API pour charger une liste de compétences vide")
	public void testGetSkills_ReturnEmpty() throws Exception {
		// Arrange
		when(skillService.getSkills()).thenReturn(Collections.emptyList());

		// Act + Assert
		mockMvc.perform(get("/skills")).andExpect(status().isOk()).andExpect(jsonPath("$.size()").value(0));
	}

	@Test
	@DisplayName("Requête API pour charger une compétence à partir de son ID")
	void testGetSkillById_ReturnCorrectSkill() throws Exception {
		// Arrange
		SkillDTO searchSkill = SkillAssertions.getSkillDTO();
		Mockito.when(skillService.getSkillByID(searchSkill.getId())).thenReturn(searchSkill);

		// Act + Assert
		mockMvc.perform(get("/skills/id/" + searchSkill.getId())).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(searchSkill.getId()))
				.andExpect(jsonPath("$.name").value(searchSkill.getName()))
				.andExpect(jsonPath("$.enable").value(searchSkill.isEnable()));

	}

	@Test
	@DisplayName("Requête API pour charger une compétence à partir d'un ID inexistant")
	public void testGetSkillById_NotFound() throws Exception {
		// Arrange
		when(skillService.getSkillByID(99)).thenThrow(new RessourceNotFoundException("Skill not found"));

		// Act + Assert
		mockMvc.perform(get("/skills/id/99")).andExpect(status().isNotFound());
	}

}
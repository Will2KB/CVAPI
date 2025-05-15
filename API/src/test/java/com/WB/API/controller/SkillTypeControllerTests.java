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

import com.WB.API.assertions.SkillTypeAssertions;
import com.WB.API.assertions.TestDatas;
import com.WB.API.dto.SkillTypeDTO;
import com.WB.API.exceptions.RessourceNotFoundException;
import com.WB.API.model.SkillType;
import com.WB.API.service.SkillTypeService;

@ActiveProfiles("test")
@DisplayName("Test du controleur des types de compétence")
@WebMvcTest(controllers = SkillTypeController.class)
class SkillTypeControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SkillTypeService skillTypeService;

	@Test
	@DisplayName("Requête API pour charger toutes les types de compétences")
	void testGetSkillTypes_ReturnAllSkillTypes() throws Exception {
		// Arrange
		TestDatas<SkillType, SkillTypeDTO> datas = SkillTypeAssertions.getSkillTypeTestDatas(3);
		Mockito.when(skillTypeService.getSkillTypes()).thenReturn(datas.dtos);

		// Act + Assert
		mockMvc.perform(get("/skillTypes")).andExpect(status().isOk())
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
	@DisplayName("Requête API pour charger une liste de types de compétence vide")
	public void testGetSkillTypes_ReturnEmpty() throws Exception {
		// Arrange
		when(skillTypeService.getSkillTypes()).thenReturn(Collections.emptyList());

		// Act + Assert
		mockMvc.perform(get("/skillTypes")).andExpect(status().isOk()).andExpect(jsonPath("$.size()").value(0));
	}

	@Test
	@DisplayName("Requête API pour charger un type de compétence à partir de son ID")
	void testGetSkillTypeById_ReturnCorrectSkillType() throws Exception {
		// Arrange
		SkillTypeDTO searchSkillType = SkillTypeAssertions.getSkillTypeDTO();
		Mockito.when(skillTypeService.getSkillTypeByID(searchSkillType.getId())).thenReturn(searchSkillType);

		// Act + Assert
		mockMvc.perform(get("/skillTypes/id/" + searchSkillType.getId())).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(searchSkillType.getId()))
				.andExpect(jsonPath("$.name").value(searchSkillType.getName()));

	}

	@Test
	@DisplayName("Requête API pour charger un type de compétence à partir d'un ID inexistant")
	public void testGetSkillTypeById_NotFound() throws Exception {
		// Arrange
		when(skillTypeService.getSkillTypeByID(99)).thenThrow(new RessourceNotFoundException("SkillType not found"));

		// Act + Assert
		mockMvc.perform(get("/skillTypes/id/99")).andExpect(status().isNotFound());
	}

}
package com.WB.API.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content; // Correct import pour vérifier le contenu
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.WB.API.assertions.PersonAssertions;
import com.WB.API.assertions.TestSummaryDatas;
import com.WB.API.dto.PersonDTO;
import com.WB.API.dto.PersonSummaryDTO;
import com.WB.API.model.Person;
import com.WB.API.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ActiveProfiles("test")
@WebMvcTest(controllers = PersonController.class)
@DisplayName("Test du controleur des personnes")
class PersonControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private PersonService personService;

	@Test
	@DisplayName("Requête API pour charger toutes les personnes")
	void testGetPersons_ReturnAllPersons() throws Exception {
		// Arrange
		TestSummaryDatas<Person, PersonDTO, PersonSummaryDTO> datas = PersonAssertions.getPersonTestDatas(4);
		Mockito.when(personService.getPersons()).thenReturn(datas.dtoSummarries);

		// Act + Assert
		mockMvc.perform(get("/persons")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.length()").value(datas.dtoSummarries.size()))
				.andExpect(jsonPath("$[0].id").value(datas.dtoSummarries.get(0).getId()))
				.andExpect(jsonPath("$[0].name").value(datas.dtoSummarries.get(0).getName()))
				.andExpect(jsonPath("$[1].id").value(datas.dtoSummarries.get(1).getId()))
				.andExpect(jsonPath("$[1].name").value(datas.dtoSummarries.get(1).getName()))
				.andExpect(jsonPath("$[2].id").value(datas.dtoSummarries.get(2).getId()))
				.andExpect(jsonPath("$[2].name").value(datas.dtoSummarries.get(2).getName()))
				.andExpect(jsonPath("$[3].id").value(datas.dtoSummarries.get(3).getId()))
				.andExpect(jsonPath("$[3].name").value(datas.dtoSummarries.get(3).getName()));

	}

	@Test
	@DisplayName("Requête API pour charger une personne à partir de son ID")
	void testGetPersonById_ReturnCorrectPerson() throws Exception {
		// Arrange
		PersonDTO searchPerson = PersonAssertions.getPersonDTO();
		Mockito.when(personService.getPersonById(searchPerson.getId())).thenReturn(searchPerson);

		// Act + Assert
		mockMvc.perform(get("/persons/id/" + searchPerson.getId())).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(searchPerson.getId()))
				.andExpect(jsonPath("$.name").value(searchPerson.getName()));

	}

	@Test
	@DisplayName("Sauvegarde d'une personne bien définie")
	public void testSavePerson_resturnSavedPerson() throws Exception {
		// Arrange
		PersonDTO newPerson = PersonAssertions.getPersonDTO();
		when(personService.savePerson(Mockito.any(PersonDTO.class))).thenReturn(newPerson.getSummary());

		// Act + Assert
		mockMvc.perform(post("/persons").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(newPerson))).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(newPerson.getId()))
				.andExpect(jsonPath("$.name").value(newPerson.getName()))
				.andExpect(jsonPath("$.firstName").value(newPerson.getFirstName()))
				.andExpect(jsonPath("$.mail").value(newPerson.getMail()))
				.andExpect(jsonPath("$.phone").value(newPerson.getPhone()));
	}

	@Test
	@DisplayName("Sauvegarde d'une personne avec un nom vide")
	public void testSavePersonWithEmptyName_StatusFail() throws Exception {
		// Arrange
		PersonDTO invalidPerson = new PersonDTO(12, "", "Toto", "toto@gmail.com", "0698748768", "Title");
		when(personService.savePerson(Mockito.any(PersonDTO.class))).thenReturn(invalidPerson.getSummary());

		// Act + Assert
		mockMvc.perform(post("/persons").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(invalidPerson))).andExpect(status().isBadRequest());
	}

	@Test
	@DisplayName("Sauvegarde d'une personne avec un prénom vide")
	public void testSavePersonWithEmptyFirstname_StatusFail() throws Exception {
		// Arrange
		PersonDTO invalidPerson = new PersonDTO(12, "Dupont", "", "toto@gmail.com", "0609549768", "Title");
		when(personService.savePerson(Mockito.any(PersonDTO.class))).thenReturn(invalidPerson.getSummary());

		// Act + Assert
		mockMvc.perform(post("/persons").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(invalidPerson))).andExpect(status().isBadRequest());
	}

	@ParameterizedTest
	@DisplayName("Sauvegarde d'une personne avec un mail incorrect")
	@ValueSource(strings = { "", "totogmail.com", "toto@gmail", "toto@", "@gmail.com", "toto.tutu@gmail" })
	public void testSaveExperienceWithIncorrectMail_StatusFail(String mail) throws Exception {
		// Arrange
		PersonDTO invalidPerson = new PersonDTO(12, "Dupont", "Toto", mail, "0609548768", "Title");
		when(personService.savePerson(Mockito.any(PersonDTO.class))).thenReturn(invalidPerson.getSummary());

		// Act + Assert
		mockMvc.perform(post("/persons").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(invalidPerson))).andExpect(status().isBadRequest());
	}

	@ParameterizedTest
	@DisplayName("Sauvegarde d'une personne avec un téléphonne incorrect")
	@ValueSource(strings = { "0", "33", "0123", "+123", "6566873265", "+336487984113567898654313587687654",
			"01234567890123456789", "33612345678", "+33/612345678", "+33 6A 12 34 56 78", "++33612345678",
			"+001123456789", "+33612345678!", "+33612345678@" })
	public void testSaveExperienceWithIncorrectPhone_StatusFail(String phone) throws Exception {
		// Arrange
		PersonDTO invalidPerson = new PersonDTO(12, "Dupont", "Toto", "toto@gmail.com", phone, "Title");
		when(personService.savePerson(Mockito.any(PersonDTO.class))).thenReturn(invalidPerson.getSummary());

		// Act + Assert
		mockMvc.perform(post("/persons").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(invalidPerson))).andExpect(status().isBadRequest());
	}
}
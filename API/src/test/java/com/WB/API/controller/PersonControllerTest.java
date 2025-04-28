package com.WB.API.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content; // Correct import pour vérifier le contenu
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
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

	private List<Person> mockedListPersons;

	@BeforeEach
	public void loadData() {
		mockedListPersons = new ArrayList<Person>();
		mockedListPersons.add(new Person(12, "Dupont", "Toto", "toto@gmail.com", "0609548768"));
		mockedListPersons.add(new Person(13, "Madrigal", "Coco", "Madrigal@gmail.com", "0609688768"));
		mockedListPersons.add(new Person(14, "Herrault", "Rodolphe", "roro@gmail.com", "0609549868"));
		mockedListPersons.add(new Person(15, "Paramount", "Thibault", "Paramount@gmail.com", "0607548768"));
	}

	@Test
	@DisplayName("Requête API pour charger toutes les personnes")
	void testGetPersons_ReturnAllPersons() throws Exception {
		Mockito.when(personService.getPersons()).thenReturn(mockedListPersons);

		mockMvc.perform(get("/persons")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.length()").value(mockedListPersons.size()))
				.andExpect(jsonPath("$[0].id").value(mockedListPersons.get(0).getId()))
				.andExpect(jsonPath("$[0].name").value(mockedListPersons.get(0).getName()))
				.andExpect(jsonPath("$[1].id").value(mockedListPersons.get(1).getId()))
				.andExpect(jsonPath("$[1].name").value(mockedListPersons.get(1).getName()))
				.andExpect(jsonPath("$[2].id").value(mockedListPersons.get(2).getId()))
				.andExpect(jsonPath("$[2].name").value(mockedListPersons.get(2).getName()))
				.andExpect(jsonPath("$[3].id").value(mockedListPersons.get(3).getId()))
				.andExpect(jsonPath("$[3].name").value(mockedListPersons.get(3).getName()));

	}

	@Test
	@DisplayName("Requête API pour charger une personne à partir de son ID")
	void testGetPersonById_ReturnCorrectPerson() throws Exception {
		Person searchPerson = mockedListPersons.get(2);
		Mockito.when(personService.getPersonById(searchPerson.getId())).thenReturn(searchPerson);

		mockMvc.perform(get("/persons/id/" + searchPerson.getId())).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(searchPerson.getId()))
				.andExpect(jsonPath("$.name").value(searchPerson.getName()));

	}

	@Test
	@DisplayName("Sauvegarde d'une personne bien définie")
	public void testSavePerson_resturnSavedPerson() throws Exception {

		Person newPerson = new Person(12, "Dupont", "Toto", "toto@gmail.com", "0609548768");
		when(personService.savePerson(Mockito.any(Person.class))).thenReturn(newPerson);

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
		Person invalidPerson = new Person(12, "", "Toto", "toto@gmail.com", "0698748768");
		when(personService.savePerson(Mockito.any(Person.class))).thenReturn(invalidPerson);

		mockMvc.perform(post("/persons").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(invalidPerson))).andExpect(status().isBadRequest());
	}

	@Test
	@DisplayName("Sauvegarde d'une personne avec un prénom vide")
	public void testSavePersonWithEmptyFirstname_StatusFail() throws Exception {
		Person invalidPerson = new Person(12, "Dupont", "", "toto@gmail.com", "0609549768");
		when(personService.savePerson(Mockito.any(Person.class))).thenReturn(invalidPerson);

		mockMvc.perform(post("/persons").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(invalidPerson))).andExpect(status().isBadRequest());
	}

	@ParameterizedTest
	@DisplayName("Sauvegarde d'une personne avec un mail incorrect")
	@ValueSource(strings = { "", "totogmail.com", "toto@gmail", "toto@", "@gmail.com", "toto.tutu@gmail" })
	public void testSaveExperienceWithIncorrectMail_StatusFail(String mail) throws Exception {
		Person invalidPerson = new Person(12, "Dupont", "Toto", mail, "0609548768");
		when(personService.savePerson(Mockito.any(Person.class))).thenReturn(invalidPerson);

		mockMvc.perform(post("/persons").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(invalidPerson))).andExpect(status().isBadRequest());
	}

	@ParameterizedTest
	@DisplayName("Sauvegarde d'une personne avec un téléphonne incorrect")
	@ValueSource(strings = { "0", "33", "0123", "+123", "6566873265", "+336487984113567898654313587687654",
			"01234567890123456789", "33612345678", "+33/612345678", "+33 6A 12 34 56 78", "++33612345678",
			"+001123456789", "+33612345678!", "+33612345678@" })
	public void testSaveExperienceWithIncorrectPhone_StatusFail(String phone) throws Exception {
		Person invalidPerson = new Person(12, "Dupont", "Toto", "toto@gmail.com", phone);
		when(personService.savePerson(Mockito.any(Person.class))).thenReturn(invalidPerson);

		mockMvc.perform(post("/persons").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(invalidPerson))).andExpect(status().isBadRequest());
	}
}
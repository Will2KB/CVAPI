package com.WB.API.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import com.WB.API.assertions.PersonAssertions;
import com.WB.API.assertions.TestSummaryDatas;
import com.WB.API.dto.PersonDTO;
import com.WB.API.dto.PersonSummaryDTO;
import com.WB.API.model.Person;
import com.WB.API.repository.PersonRepository;

@ActiveProfiles("test")
@DisplayName("Test du service des personnes")
@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

	@Mock
	private PersonRepository personRepository;

	@InjectMocks
	private PersonService personService;

	@Test
	@DisplayName("Sauvegarde d'une personne")
	void savePerson_ReturnsSavedPerson() {

		PersonDTO mockedPerson = PersonAssertions.getPersonDTO();
		Person input = new Person(mockedPerson.getId(), mockedPerson.getName(), mockedPerson.getFirstName(),
				mockedPerson.getMail(), mockedPerson.getPhone(), mockedPerson.getTitle(), mockedPerson.getSubtitle(),
				mockedPerson.getBirthDate(), mockedPerson.getPersonalValues());

		Mockito.when(personRepository.save(Mockito.any(Person.class))).thenReturn(input);

		PersonSummaryDTO result = personService.savePerson(mockedPerson);

		PersonAssertions.assertNotNullDTO(result);
		PersonAssertions.assertEqualsProperties(input, result);
	}

	@Test
	@DisplayName("Recherche d'une personne à partir de l'ID")
	void findById_ShouldReturnCorrectPerson() {
		PersonDTO mockedPerson = PersonAssertions.getPersonDTO();
		Person output = new Person(mockedPerson.getId(), mockedPerson.getName(), mockedPerson.getFirstName(),
				mockedPerson.getMail(), mockedPerson.getPhone(), mockedPerson.getTitle(), mockedPerson.getSubtitle(),
				mockedPerson.getBirthDate(), mockedPerson.getPersonalValues());
		Mockito.when(personRepository.findById(mockedPerson.getId())).thenReturn(Optional.of(output));
		PersonDTO result = personService.getPersonById(mockedPerson.getId());

		PersonAssertions.assertNotNullDTO(result);
		PersonAssertions.assertEqualsProperties(mockedPerson, result);
	}

	@Test
	@DisplayName("Recherche d'une personne à partir de son nom et son prénom")
	void findByName_ShouldReturnCorrectPerson() {
		PersonDTO mockedPerson = PersonAssertions.getPersonDTO();
		Person output = new Person(mockedPerson.getId(), mockedPerson.getName(), mockedPerson.getFirstName(),
				mockedPerson.getMail(), mockedPerson.getPhone(), mockedPerson.getTitle(), mockedPerson.getSubtitle(),
				mockedPerson.getBirthDate(), mockedPerson.getPersonalValues());
		Mockito.when(
				personRepository.findFirstPersonByNameAndFirstName(mockedPerson.getName(), mockedPerson.getFirstName()))
				.thenReturn(output);
		PersonDTO result = personService.getPersonByName(mockedPerson.getName(), mockedPerson.getFirstName());

		PersonAssertions.assertNotNullDTO(result);
		PersonAssertions.assertEqualsProperties(mockedPerson, result);
	}

	@Test
	@DisplayName("Recherche d'une personne à partir de son nom et son prénom sans le trouver")
	void findByName_ShouldReturnNull() {
		Mockito.when(personRepository.findFirstPersonByNameAndFirstName("", "")).thenReturn(null);
		PersonDTO result = personService.getPersonByName("", "");
		Assertions.assertNull(result);
	}

	@Test
	@DisplayName("Recherche d'une personne à partir de son email")
	void findByEmail_ShouldReturnCorrectPerson() {
		PersonDTO mockedPerson = PersonAssertions.getPersonDTO();
		Person output = new Person(mockedPerson.getId(), mockedPerson.getName(), mockedPerson.getFirstName(),
				mockedPerson.getMail(), mockedPerson.getPhone(), mockedPerson.getTitle(), mockedPerson.getSubtitle(),
				mockedPerson.getBirthDate(), mockedPerson.getPersonalValues());
		Mockito.when(personRepository.findFirstPersonByMail(mockedPerson.getMail())).thenReturn(output);
		PersonDTO result = personService.getPersonByEmail(mockedPerson.getMail());

		PersonAssertions.assertNotNullDTO(result);
		PersonAssertions.assertEqualsProperties(mockedPerson, result);
	}

	@Test
	@DisplayName("Recherche d'une personne à partir de son email sans le trouver")
	void findByEmail_ShouldReturnNull() {
		Mockito.when(personRepository.findFirstPersonByMail("")).thenReturn(null);
		PersonDTO result = personService.getPersonByEmail("");
		Assertions.assertNull(result);
	}

	@Test
	@DisplayName("Chargement de toutes les personnes")
	void findAll_ShouldReturnListOfAllPersons() {
		TestSummaryDatas<Person, PersonDTO, PersonSummaryDTO> datas = PersonAssertions.getSkillTestDatas(4);
		Mockito.when(personRepository.findAll()).thenReturn(datas.entities);

		List<PersonSummaryDTO> loadedPersons = personService.getPersons();

		PersonAssertions.assertListEntitiesSummaries(datas.entities, loadedPersons);
	}

	@Test
	@DisplayName("Test du calcul de l'âge")
	void calculAgeFromBirthdate_ShouldReturnAge() {
		Person person = PersonAssertions.getPerson();
		person.setBirthdate(LocalDate.now().minusYears(38));
		int age = personService.getAge(person);
		Assertions.assertEquals(38, age);
	}

	@Test
	@DisplayName("Test du calcul de l'âge à partir d'une date de naissance null")
	void calculAgeFromNullBirthdate_ShouldReturnNull() {
		Person person = PersonAssertions.getPerson();
		person.setBirthdate(null);
		int age = personService.getAge(person);
		Assertions.assertEquals(0, age);
	}
}
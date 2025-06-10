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
import com.WB.API.exceptions.RessourceNotFoundException;
import com.WB.API.model.Person;
import com.WB.API.repository.PersonRepository;

/*
 * Tests du service des personnes
 * Lors de ces test, nous souhaitons vérifier le fonctionnement du service 
 * c'est pourquoi le comportement du repository (auquel le service fait appel) sera mocké
 */
@ActiveProfiles("test")
@DisplayName("Test du service des personnes")
@ExtendWith(MockitoExtension.class)
class PersonServiceTests {

	@Mock
	private PersonRepository personRepository;

	@InjectMocks
	private PersonService personService;

	@Test
	@DisplayName("Sauvegarde d'une personne")
	void savePerson_ReturnsSavedPerson() {
		// Arrange
		PersonDTO mockedPerson = PersonAssertions.getPersonDTO();
		Person input = new Person(mockedPerson.getId(), mockedPerson.getName(), mockedPerson.getFirstName(),
				mockedPerson.getMail(), mockedPerson.getPhone(), mockedPerson.getTitle(), mockedPerson.getSubtitle(),
				mockedPerson.getBirthDate(), mockedPerson.getPersonalValues(), mockedPerson.getLinkedInLink(),
				mockedPerson.getGitHubLink());
		Mockito.when(personRepository.save(Mockito.any(Person.class))).thenReturn(input);

		// Act
		PersonSummaryDTO result = personService.savePerson(mockedPerson);

		// Assert
		PersonAssertions.assertNotNullDTO(result);
		PersonAssertions.assertEqualsProperties(input, result);
	}

	@Test
	@DisplayName("Recherche d'une personne à partir de l'ID")
	void findById_ShouldReturnCorrectPerson() {
		// Arrange
		PersonDTO mockedPerson = PersonAssertions.getPersonDTO();
		Person output = new Person(mockedPerson.getId(), mockedPerson.getName(), mockedPerson.getFirstName(),
				mockedPerson.getMail(), mockedPerson.getPhone(), mockedPerson.getTitle(), mockedPerson.getSubtitle(),
				mockedPerson.getBirthDate(), mockedPerson.getPersonalValues(), mockedPerson.getLinkedInLink(),
				mockedPerson.getGitHubLink());
		Mockito.when(personRepository.findById(mockedPerson.getId())).thenReturn(Optional.of(output));

		// Act
		PersonDTO result = personService.getPersonById(mockedPerson.getId());

		// Assert
		PersonAssertions.assertNotNullDTO(result);
		PersonAssertions.assertEqualsProperties(mockedPerson, result);
	}

	@Test
	@DisplayName("Recherche d'une personne à partir d'un ID inexistant")
	void findById_ShouldReturnNullWhenNotFound() {
		// Arrange
		Integer id = 999;
		Mockito.when(personRepository.findById(id)).thenReturn(Optional.empty());

		// Act + Assert
		RessourceNotFoundException exception = Assertions.assertThrows(RessourceNotFoundException.class,
				() -> personService.getPersonById(id));

		// Vérification du message de l'exception
		Assertions.assertEquals("Person not found with id: " + id, exception.getMessage());
	}

	@Test
	@DisplayName("Recherche d'une personne à partir de son nom et son prénom")
	void findByName_ShouldReturnCorrectPerson() {
		// Arrange
		PersonDTO mockedPerson = PersonAssertions.getPersonDTO();
		Person output = new Person(mockedPerson.getId(), mockedPerson.getName(), mockedPerson.getFirstName(),
				mockedPerson.getMail(), mockedPerson.getPhone(), mockedPerson.getTitle(), mockedPerson.getSubtitle(),
				mockedPerson.getBirthDate(), mockedPerson.getPersonalValues(), mockedPerson.getLinkedInLink(),
				mockedPerson.getGitHubLink());
		Mockito.when(
				personRepository.findFirstPersonByNameAndFirstName(mockedPerson.getName(), mockedPerson.getFirstName()))
				.thenReturn(output);

		// Act
		PersonDTO result = personService.getPersonByName(mockedPerson.getName(), mockedPerson.getFirstName());

		// Assert
		PersonAssertions.assertNotNullDTO(result);
		PersonAssertions.assertEqualsProperties(mockedPerson, result);
	}

	@Test
	@DisplayName("Recherche d'une personne à partir de son nom et son prénom sans le trouver")
	void findByName_ShouldReturnNullWhenNotFound() {
		// Arrange
		String name = "Not";
		String firstName = "Found";
		Mockito.when(personRepository.findFirstPersonByNameAndFirstName(name, firstName)).thenReturn(null);

		// Act + Assert
		RessourceNotFoundException exception = Assertions.assertThrows(RessourceNotFoundException.class,
				() -> personService.getPersonByName(name, firstName));

		// Vérification du message de l'exception
		Assertions.assertEquals("Person not found with name : " + name + " and firstName : " + firstName,
				exception.getMessage());

	}

	@Test
	@DisplayName("Recherche d'une personne à partir de son email")
	void findByEmail_ShouldReturnCorrectPerson() {
		// Arrange
		PersonDTO mockedPerson = PersonAssertions.getPersonDTO();
		Person output = new Person(mockedPerson.getId(), mockedPerson.getName(), mockedPerson.getFirstName(),
				mockedPerson.getMail(), mockedPerson.getPhone(), mockedPerson.getTitle(), mockedPerson.getSubtitle(),
				mockedPerson.getBirthDate(), mockedPerson.getPersonalValues(), mockedPerson.getLinkedInLink(),
				mockedPerson.getGitHubLink());
		Mockito.when(personRepository.findFirstPersonByMail(mockedPerson.getMail())).thenReturn(output);

		// Act
		PersonDTO result = personService.getPersonByEmail(mockedPerson.getMail());

		// Assert
		PersonAssertions.assertNotNullDTO(result);
		PersonAssertions.assertEqualsProperties(mockedPerson, result);
	}

	@Test
	@DisplayName("Recherche d'une personne à partir de son email sans le trouver")
	void findByEmail_ShouldReturnNullWhenNotFound() {
		// Arrange
		String email = "Not@Found.com";
		Mockito.when(personRepository.findFirstPersonByMail(email)).thenReturn(null);

		// Act + Assert
		RessourceNotFoundException exception = Assertions.assertThrows(RessourceNotFoundException.class,
				() -> personService.getPersonByEmail(email));

		// Vérification du message de l'exception
		Assertions.assertEquals("Person not found with email: " + email, exception.getMessage());
	}

	@Test
	@DisplayName("Chargement de toutes les personnes")
	void findAll_ShouldReturnListOfAllPersons() {
		// Arrange
		TestSummaryDatas<Person, PersonDTO, PersonSummaryDTO> datas = PersonAssertions.getPersonTestDatas(4);
		Mockito.when(personRepository.findAll()).thenReturn(datas.entities);

		// Act
		List<PersonSummaryDTO> loadedPersons = personService.getPersons();

		// Assert
		PersonAssertions.assertListEntitiesSummaries(datas.entities, loadedPersons);
	}

	@Test
	@DisplayName("Chargement d'une liste vide si la base de données est vide")
	void findAll_ShouldReturnEmptyListWhenBDDEmpty() {
		// Arrange
		Mockito.when(personRepository.findAll()).thenReturn(List.of());

		// Act + Assert
		RessourceNotFoundException exception = Assertions.assertThrows(RessourceNotFoundException.class,
				() -> personService.getPersons());

		// Vérification du message de l'exception
		Assertions.assertEquals("List of summaries of persons is empty.", exception.getMessage());
	}

	@Test
	@DisplayName("Test du calcul de l'âge")
	void calculAgeFromBirthdate_ShouldReturnAge() {
		// Arrange
		Person person = PersonAssertions.getPerson();
		person.setBirthdate(LocalDate.now().minusYears(38));

		// Act
		int age = personService.getAge(person);

		// Assert
		Assertions.assertEquals(38, age);
	}

	@Test
	@DisplayName("Test du calcul de l'âge avec date anniversaire à venir")
	void calculAgeWithUpcomingBirthday_ShouldReturnCorrectAge() {
		// Arrange
		LocalDate birthDate = LocalDate.now().minusYears(30).plusDays(1); // Demain = anniversaire
		Person person = PersonAssertions.getPerson();
		person.setBirthdate(birthDate);

		// Act
		int age = personService.getAge(person);

		// Assert
		Assertions.assertEquals(29, age);
	}

	@Test
	@DisplayName("Test du calcul de l'âge à partir d'une date de naissance null")
	void calculAgeFromNullBirthdate_ShouldReturnNull() {
		// Arrange
		Person person = PersonAssertions.getPerson();
		person.setBirthdate(null);

		// Act
		int age = personService.getAge(person);

		Assertions.assertEquals(0, age);
	}
}
package com.WB.API.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.WB.API.assertions.PersonAssertions;
import com.WB.API.model.Person;

/*
 * Tests du repository des personnes
 * Lors de ces test, nous souhaitons vérifier le fonctionnement du repository 
 * Ces tests seront effectué sur une base de test H2
 */
@DataJpaTest
@ActiveProfiles("test")
@DisplayName("Test du repository des personnes")
class PersonRepositoryTests {

	@Autowired
	private PersonRepository personRepository;

	private List<Person> persons;

	/**
	 * Chargement des données de tests spécifique au repository
	 */
	@BeforeEach
	void loadData() {

		persons = new ArrayList<>();
		Person person1 = new Person("Madrigal", "Coco", "MadrigalCoco@gmail.com", "0609548768", "Title1", "Subtitle1",
				LocalDate.of(1986, 07, 04), "Values1", "LinkedIn1", "Github1");
		personRepository.save(person1);
		persons.add(person1);

		Person person2 = new Person("Dupont", "Corine", "Dupont@gmail.com", "0609632768", "Title2", "Subtitle2",
				LocalDate.of(1995, 12, 15), "Values2", "LinkedIn2", "Github2");
		personRepository.save(person2);
		persons.add(person2);

		Person person3 = new Person("Papa", "Thibault", "PapaThibault@gmail.com", "0606548768", "Title3", "Subtitle3",
				LocalDate.of(1975, 02, 07), "Values3", "LinkedIn3", "Github3");
		personRepository.save(person3);
		persons.add(person3);

		Person person4 = new Person("Herrault", "Rodolphe", "roro@gmail.com", "0609598768", "Title4", "Subtitle4",
				LocalDate.of(1984, 10, 12), "Values4", "LinkedIn4", "Github4");
		personRepository.save(person4);
		persons.add(person4);

		Person person5 = new Person("Paramount", "Thibault", "ParamountThibault@gmail.com", "0606546498", "Title5",
				"Subtitle5", LocalDate.of(1989, 9, 9), "Values5", "LinkedIn5", "Github5");
		personRepository.save(person5);
		persons.add(person5);

		Person person6 = new Person("Herrault", "Alicia", "alicia@gmail.com", "0695478768", "Title6", "Subtitle6",
				LocalDate.of(1992, 03, 16), "Values6", "LinkedIn6", "Github6");
		personRepository.save(person6);
		persons.add(person6);
	}

	@Test
	@DisplayName("Sauvegarde d'une nouvelle personne")
	void savePerson_RetrieveIt() {
		// Arrange
		Person newPerson = new Person("New", "Person", "NewPerson@gmail.com", "0665485768", "Title1", "Subtitle1",
				LocalDate.of(1986, 07, 04), "Values1", "LinkedIn1", "Github1");

		// Act
		Person savedPerson = personRepository.save(newPerson);

		// Assert
		PersonAssertions.assertNotNullEntity(savedPerson);
		Assertions.assertTrue(savedPerson.getId() > 0, "L'id enregistré n'est pas supérieur à 0");

		// Vérification que la personne est bien en base
		Optional<Person> optPerson = personRepository.findById(savedPerson.getId());
		Assertions.assertTrue(optPerson.isPresent());
		Person findPerson = optPerson.get();
		PersonAssertions.assertEqualsProperties(savedPerson, findPerson);
	}

	@Test
	@DisplayName("Chargement d'une personne à partir de son ID")
	void findPersonById_ReturnCorrectPerson() {
		// Arrange
		Person searchPerson = persons.get(2);

		// Act
		Optional<Person> optPerson = personRepository.findById(searchPerson.getId());

		// Assert
		Assertions.assertTrue(optPerson.isPresent(),
				"La personne n'a pas été trouvée pour l'ID " + searchPerson.getId());
		Person findPerson = optPerson.get();
		PersonAssertions.assertEqualsProperties(searchPerson, findPerson);
	}

	@Test
	@DisplayName("Chargement d'une personne à partir d'un ID inexistant")
	void findPersonById_ReturnNullWhenNotFound() {
		// Arrange
		Integer id = 999;

		// Act
		Optional<Person> optPerson = personRepository.findById(id);

		// Assert
		Assertions.assertFalse(optPerson.isPresent(), "La personne a été trouvée pour l'ID " + id);
	}

	@ParameterizedTest
	@DisplayName("Chargement d'une personne à partir de son nom")
	@ValueSource(ints = { 0, 1, 2, 3, 4, 5 })
	void findPersonByName_ReturnCorrectPerson(int searchId) {
		// Arrange
		Person searchPerson = persons.get(searchId);

		// Act
		Person findPerson = personRepository.findFirstPersonByNameAndFirstName(searchPerson.getName(),
				searchPerson.getFirstName());

		// Assert
		PersonAssertions.assertEqualsProperties(searchPerson, findPerson);
	}

	@Test
	@DisplayName("Chargement d'une personne à partir d'un nom inexistant")
	void findPersonByName_ReturnNullPersonWhenNotFound() {
		// Arrange
		String name = "Not";
		String firstName = "Found";

		// Act
		Person findPerson = personRepository.findFirstPersonByNameAndFirstName(name, firstName);

		// Assert
		Assertions.assertNull(findPerson, "Une personne a été trouvé avec le nom et prénom: " + name + " " + firstName);
	}

	@ParameterizedTest
	@DisplayName("Chargement d'une personne à partir de son email")
	@ValueSource(ints = { 0, 1, 2, 3, 4, 5 })
	void findPersonByEmail_ReturnCorrectPerson(int searchId) {
		// Arrange
		Person searchPerson = persons.get(searchId);

		// Act
		Person findPerson = personRepository.findFirstPersonByMail(searchPerson.getMail());

		// Assert
		PersonAssertions.assertEqualsProperties(searchPerson, findPerson);
	}

	@Test
	@DisplayName("Chargement d'une personne à partir d'un email inexistant")
	void findPersonByEmail_ReturnCorrectPerson() {
		// Arrange
		String email = "Not@Found.com";

		// Act
		Person findPerson = personRepository.findFirstPersonByMail(email);

		// Assert
		Assertions.assertNull(findPerson, "Une personne a été trouvé avec l'email: " + email);
	}

	@Test
	@DisplayName("Chargement de toutes les personnes")
	void findAllPersons_ReturnCorrectList() {
		// Arrange @BeforeEach

		// Act
		List<Person> findPersons = personRepository.findAll();

		// Assert
		for (Person expectedPerson : persons) {
			Person actualPerson = findPersons.stream().filter(c -> c.getName().equals(expectedPerson.getName()))
					.filter(c -> c.getFirstName().equals(expectedPerson.getFirstName())).findFirst().orElse(null);
			PersonAssertions.assertEqualsProperties(expectedPerson, actualPerson);
		}
	}

}

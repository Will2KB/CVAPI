package com.WB.API.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.WB.API.model.Person;

@DataJpaTest
@ActiveProfiles("test")
@DisplayName("Test du repository des personnes")
class PersonRepositoryTest {

	@Autowired
	private PersonRepository personRepository;

	private List<Person> persons;

	@BeforeEach
	void loadData() {

		persons = new ArrayList<>();

		Person person1 = new Person("Madrigal", "Coco", "MadrigalCoco@gmail.com", "0609548768");
		personRepository.save(person1);
		persons.add(person1);

		Person person2 = new Person("Dupont", "Corine", "Dupont@gmail.com", "0609632768");
		personRepository.save(person2);
		persons.add(person2);

		Person person3 = new Person("Paramount", "Thibault", "MadrigalCoco@gmail.com", "0606548768");
		personRepository.save(person3);
		persons.add(person3);

		Person person4 = new Person("Herrault", "Rodolphe", "roro@gmail.com", "0609598768");
		personRepository.save(person4);
		persons.add(person4);
	}

	@Test
	@DisplayName("Sauvegarde d'une nouvelle personne")
	void savePerson_RetrieveIt() {
		Person newPerson = new Person();
		newPerson.setName("Dupont");
		newPerson.setFirstName("Toto");
		newPerson.setMail("toto@gmail.com");
		Person savedPerson = personRepository.save(newPerson);

		Assertions.assertNotNull(savedPerson);
		Assertions.assertNotNull(savedPerson.getId());
		Assertions.assertTrue(savedPerson.getId() > 0);

		// Vérification que la personne est bien en base
		Optional<Person> optPerson = personRepository.findById(savedPerson.getId());
		Assertions.assertTrue(optPerson.isPresent());
		Person findPerson = optPerson.get();
		Assertions.assertEquals(newPerson.getName(), findPerson.getName());
		Assertions.assertEquals(newPerson.getFirstName(), findPerson.getFirstName());
		Assertions.assertEquals(newPerson.getMail(), findPerson.getMail());
	}

	@Test
	@DisplayName("Chargement d'une personne à partir de son ID")
	void findPersonById_ReturnCorrectPerson() {
		Person searchPerson = persons.get(2);
		Optional<Person> optPerson = personRepository.findById(searchPerson.getId());

		Assertions.assertTrue(optPerson.isPresent(),
				"La personne n'a pas été trouvée pour l'ID " + searchPerson.getId());
		Person findPerson = optPerson.get();

		Assertions.assertNotNull(findPerson);
		Assertions.assertEquals(searchPerson.getId(), findPerson.getId());
		Assertions.assertEquals(searchPerson.getName(), findPerson.getName());
		Assertions.assertEquals(searchPerson.getFirstName(), findPerson.getFirstName());
		Assertions.assertEquals(searchPerson.getMail(), findPerson.getMail());
	}

	@Test
	@DisplayName("Chargement de toutes les personnes")
	void findAllPersons_ReturnCorrectList() {
		List<Person> findPersons = personRepository.findAll();

		for (Person expectedPerson : persons) {
			Person actualPerson = findPersons.stream().filter(c -> c.getName().equals(expectedPerson.getName()))
					.findFirst().orElse(null);

			Assertions.assertNotNull(actualPerson);
			Assertions.assertEquals(expectedPerson.getId(), actualPerson.getId());
			Assertions.assertEquals(expectedPerson.getName(), actualPerson.getName());
			Assertions.assertEquals(expectedPerson.getFirstName(), actualPerson.getFirstName());
			Assertions.assertEquals(expectedPerson.getMail(), actualPerson.getMail());
		}
	}

}

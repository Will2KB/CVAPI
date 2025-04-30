package com.WB.API.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import com.WB.API.dto.PersonDTO;
import com.WB.API.dto.PersonSummaryDTO;
import com.WB.API.mapper.PersonMapper;
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

	private List<PersonDTO> mockedListPersons;

	@BeforeEach
	private void LoadData() {
		mockedListPersons = new ArrayList<>();
		mockedListPersons.add(new PersonDTO(12, "Dupont", "Toto", "toto@gmail.com", "0609548768"));
		mockedListPersons.add(new PersonDTO(13, "Madrigal", "Coco", "Madrigal@gmail.com", "0606348768"));
		mockedListPersons.add(new PersonDTO(14, "Herrault", "Rodolphe", "roro@gmail.com", "0609565768"));
		mockedListPersons.add(new PersonDTO(15, "Paramount", "Thibault", "Paramount@gmail.com", "0609658768"));

	}

	private List<Person> getMockedListPersonEntity() {
		List<Person> persons = new ArrayList<>();

		for (PersonDTO personDTO : mockedListPersons) {
			persons.add(PersonMapper.toEntity(personDTO));
		}

		return persons;
	}

	@Test
	@DisplayName("Sauvegarde d'une personne")
	void savePerson_ReturnsSavedPerson() {

		PersonDTO mockedPerson = mockedListPersons.get(2);
		Person input = new Person(mockedPerson.getId(), mockedPerson.getName(), mockedPerson.getFirstName(),
				mockedPerson.getMail(), mockedPerson.getPhone());

		Mockito.when(personRepository.save(Mockito.any(Person.class))).thenReturn(input);

		PersonSummaryDTO result = personService.savePerson(mockedPerson);

		Assertions.assertNotNull(result.getId());
		Assertions.assertEquals(input.getName(), result.getName());
		Assertions.assertEquals(input.getFirstName(), result.getFirstName());
		Assertions.assertEquals(input.getMail(), result.getMail());
		Assertions.assertEquals(input.getPhone(), result.getPhone());
	}

	@Test
	@DisplayName("Recherche d'une personne à partir de l'ID")
	void findById_ShouldReturnCorrectPerson() {
		PersonDTO mockedPerson = mockedListPersons.get(2);
		Person output = new Person(mockedPerson.getId(), mockedPerson.getName(), mockedPerson.getFirstName(),
				mockedPerson.getMail(), mockedPerson.getPhone());
		Mockito.when(personRepository.findById(mockedPerson.getId())).thenReturn(Optional.of(output));
		PersonDTO result = personService.getPersonById(mockedPerson.getId());

		Assertions.assertNotNull(result);
		Assertions.assertEquals(mockedPerson.getName(), result.getName());
		Assertions.assertEquals(mockedPerson.getFirstName(), result.getFirstName());
		Assertions.assertEquals(mockedPerson.getMail(), result.getMail());
		Assertions.assertEquals(mockedPerson.getPhone(), result.getPhone());
	}

	@Test
	@DisplayName("Chargement de toutes les personnes")
	void findAll_ShouldReturnListOfAllPersons() {
		Mockito.when(personRepository.findAll()).thenReturn(this.getMockedListPersonEntity());

		List<PersonSummaryDTO> loadedPersons = personService.getPersons();

		for (PersonDTO expectedPerson : mockedListPersons) {
			PersonSummaryDTO actualPerson = loadedPersons.stream()
					.filter(c -> c.getName().equals(expectedPerson.getName())).findFirst().orElse(null);

			Assertions.assertNotNull(actualPerson);
			Assertions.assertEquals(expectedPerson.getId(), actualPerson.getId());
			Assertions.assertEquals(expectedPerson.getName(), actualPerson.getName());
			Assertions.assertEquals(expectedPerson.getFirstName(), actualPerson.getFirstName());
			Assertions.assertEquals(expectedPerson.getMail(), actualPerson.getMail());
			Assertions.assertEquals(expectedPerson.getPhone(), actualPerson.getPhone());
		}
	}
}
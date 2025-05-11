package com.WB.API.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WB.API.dto.PersonDTO;
import com.WB.API.dto.PersonSummaryDTO;
import com.WB.API.mapper.PersonMapper;
import com.WB.API.model.Person;
import com.WB.API.repository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;

	public List<PersonSummaryDTO> getPersons() {
		return PersonMapper.toDTOListSummary(personRepository.findAll());
	}

	public PersonDTO getPersonById(int id) {
		Person findPerson = personRepository.findById(id).get();
		PersonDTO findPersonDTO = PersonMapper.toDTO(findPerson);

		if (findPersonDTO != null) {
			findPersonDTO.setAge(this.getAge(findPerson));
		}
		return findPersonDTO;
	}

	public PersonDTO getPersonByName(String name, String firstName) {
		Person findPerson = personRepository.findFirstPersonByNameAndFirstName(name, firstName);
		PersonDTO findPersonDTO = PersonMapper.toDTO(findPerson);

		if (findPersonDTO != null) {
			findPersonDTO.setAge(this.getAge(findPerson));
		}
		return findPersonDTO;
	}

	public PersonDTO getPersonByEmail(String email) {
		Person findPerson = personRepository.findFirstPersonByMail(email);
		PersonDTO findPersonDTO = PersonMapper.toDTO(findPerson);

		if (findPersonDTO != null) {
			findPersonDTO.setAge(this.getAge(findPerson));
		}
		return findPersonDTO;
	}

	public PersonSummaryDTO savePerson(PersonDTO person) {
		Person toSave = PersonMapper.toEntity(person);
		return PersonMapper.toSummaryDTO(personRepository.save(toSave));
	}

	public int getAge(Person person) {
		if (person.getBirthdate() == null)
			return 0;

		return Period.between(person.getBirthdate(), LocalDate.now()).getYears();
	}
}

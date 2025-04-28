package com.WB.API.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WB.API.model.Person;
import com.WB.API.repository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;

	public List<Person> getPersons() {
		return personRepository.findAll();
	}

	public Person getPersonById(int id) {
		return personRepository.findById(id).get();
	}

	public Person getPersonByName(String name) {
		return personRepository.findFirstCityByName(name);
	}

	public Person savePerson(Person person) {
		return personRepository.save(person);
	}
}

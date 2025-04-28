package com.WB.API.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.WB.API.model.Person;
import com.WB.API.service.PersonService;

import jakarta.validation.Valid;

@RestController
public class PersonController {

	@Autowired
	private PersonService personService;

	@GetMapping("/persons")
	public List<Person> getPersons() {
		return personService.getPersons();
	}

	@GetMapping("/persons/id/{id}")
	public Person getPerson(@PathVariable int id) {
		return personService.getPersonById(id);
	}

	@PostMapping("/persons")
	public Person savePerson(@Valid @RequestBody Person person) {
		return personService.savePerson(person);
	}
}

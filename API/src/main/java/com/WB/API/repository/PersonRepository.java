package com.WB.API.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.WB.API.model.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {

	public List<Person> findAll();

	public Person findFirstPersonByNameAndFirstName(String name, String firstName);

	public Person findFirstPersonByMail(String mail);
}

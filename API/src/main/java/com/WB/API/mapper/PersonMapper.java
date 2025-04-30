package com.WB.API.mapper;

import java.util.ArrayList;
import java.util.List;

import com.WB.API.dto.PersonDTO;
import com.WB.API.dto.PersonSummaryDTO;
import com.WB.API.model.Person;

public class PersonMapper {

	public static PersonSummaryDTO toSummaryDTO(Person person) {
		if (person == null)
			return null;

		PersonSummaryDTO personSummary = new PersonSummaryDTO();

		personSummary.setId(person.getId());
		personSummary.setName(person.getName());
		personSummary.setFirstName(person.getFirstName());
		personSummary.setMail(person.getMail());
		personSummary.setPhone(person.getPhone());
		personSummary.setTitle(person.getTitle());

		return personSummary;
	}

	public static List<PersonSummaryDTO> toDTOListSummary(List<Person> persons) {
		if (persons == null)
			return null;

		List<PersonSummaryDTO> summaries = new ArrayList<>();

		for (Person person : persons) {
			summaries.add(PersonMapper.toSummaryDTO(person));
		}

		return summaries;
	}

	public static PersonDTO toDTO(Person person) {
		if (person == null)
			return null;

		PersonDTO personDTO = new PersonDTO();

		personDTO.setSummary(PersonMapper.toSummaryDTO(person));
		personDTO.setSubtitle(person.getSubtitle());
		personDTO.setPersonalValues(person.getPersonalValues());
		personDTO.setAddress(AddressMapper.toDTO(person.getAddress()));
		personDTO.setBirthDate(person.getBirthdate());

		personDTO.setNationalities(NationalityMapper.toDTOList(person.getNationalities()));
		personDTO.setSpokenLanguages(SpokenLanguageMapper.toDTOList(person.getSpokenLanguages()));
		personDTO.setHobbies(HobbyMapper.toDTOList(person.getHobbies()));
		personDTO.setExperiences(ExperienceMapper.toSummaryDTOList(person.getExperiences()));

		return personDTO;
	}

	public static Person toEntity(PersonDTO personDTO) {
		if (personDTO == null)
			return null;

		Person person = new Person(personDTO.getId());

		person.setName(personDTO.getSummary().getName());
		person.setFirstName(personDTO.getSummary().getFirstName());
		person.setMail(personDTO.getSummary().getMail());
		person.setPhone(personDTO.getSummary().getPhone());
		person.setTitle(personDTO.getSummary().getTitle());

		person.setSubtitle(personDTO.getSubtitle());
		person.setPersonalValues(personDTO.getPersonalValues());
		person.setBirthdate(personDTO.getBirthDate());
		person.setAddress(AddressMapper.toEntity(personDTO.getAddress()));

		person.setNationalities(NationalityMapper.toEntityList(personDTO.getNationalities()));
		person.setSpokenLanguages(SpokenLanguageMapper.toEntityList(personDTO.getSpokenLanguages()));
		person.setHobbies(HobbyMapper.toEntityList(personDTO.getHobbies()));
		person.setExperiences(ExperienceMapper.toEntityListFromSummary(personDTO.getExperiences()));

		return person;
	}
}

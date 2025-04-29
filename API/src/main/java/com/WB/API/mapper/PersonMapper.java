package com.WB.API.mapper;

import com.WB.API.dto.PersonDTO;
import com.WB.API.dto.PersonSummaryDTO;
import com.WB.API.model.Person;

public class PersonMapper {

	public static PersonSummaryDTO toSummaryDTO(Person person) {
		PersonSummaryDTO personSummary = new PersonSummaryDTO();

		personSummary.setId(person.getId());
		personSummary.setName(person.getName());
		personSummary.setFirstName(person.getFirstName());
		personSummary.setMail(person.getMail());
		personSummary.setPhone(person.getPhone());
		personSummary.setTitle(person.getTitle());

		return personSummary;
	}

	public static PersonDTO toDTO(Person person) {
		PersonDTO personDTO = new PersonDTO();

		personDTO.setSummary(PersonMapper.toSummaryDTO(person));
		personDTO.setSubtitle(person.getSubtitle());
		personDTO.setPersonalValues(person.getPersonalValues());
		personDTO.setAddress(AddressMapper.toDTO(person.getAddress()));
		personDTO.setBirthDate(person.getBirthday());

		personDTO.setNationalities(NationalityMapper.toDTOList(person.getNationalities()));
		personDTO.setSpokenLanguages(SpokenLanguageMapper.toDTOList(person.getSpokenLanguages()));
		personDTO.setHobbies(HobbyMapper.toDTOList(person.getHobbies()));
		personDTO.setExperiences(ExperienceMapper.toSummaryDTOList(person.getExperiences()));

		return personDTO;
	}

	public static Person toEntity(PersonDTO personDTO) {
		Person person = new Person(personDTO.getId());

		person.setName(personDTO.getSummary().getName());
		person.setFirstName(personDTO.getSummary().getFirstName());
		person.setMail(personDTO.getSummary().getMail());
		person.setPhone(personDTO.getSummary().getPhone());
		person.setTitle(personDTO.getSummary().getTitle());

		person.setSubtitle(personDTO.getSubtitle());
		person.setPersonalValues(personDTO.getPersonalValues());
		person.setBirthday(personDTO.getBirthDate());
		person.setAddress(AddressMapper.toEntity(personDTO.getAddress()));

		person.setNationalities(NationalityMapper.toEntityList(personDTO.getNationalities()));
		person.setSpokenLanguages(SpokenLanguageMapper.toEntityList(personDTO.getSpokenLanguages()));
		person.setHobbies(HobbyMapper.toEntityList(personDTO.getHobbies()));
		person.setExperiences(ExperienceMapper.toEntityListFromSummary(personDTO.getExperiences()));

		return person;
	}
}

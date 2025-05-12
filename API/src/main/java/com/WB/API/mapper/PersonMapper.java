package com.WB.API.mapper;

import java.util.ArrayList;
import java.util.List;

import com.WB.API.dto.PersonDTO;
import com.WB.API.dto.PersonSummaryDTO;
import com.WB.API.model.Person;

/*
 * Classe permettant de mapper une entité en objet de trasnfert et inverssement
 * pour les Person
 */
public class PersonMapper {

	/*
	 * Transfert une entité en objet de transfert résumé
	 * 
	 * @Param person: Entité à mapper
	 * 
	 * @Return Retourne l'objet de transfert résumé après le mappage
	 */
	public static PersonSummaryDTO toSummaryDTO(Person person) {
		// Si l'entité d'entrée est null => on retroune null
		if (person == null)
			return null;

		// Création de l'objet de transfert résumé
		PersonSummaryDTO personSummary = new PersonSummaryDTO();

		// Mappage des paramètres
		personSummary.setId(person.getId());
		personSummary.setName(person.getName());
		personSummary.setFirstName(person.getFirstName());
		personSummary.setMail(person.getMail());
		personSummary.setPhone(person.getPhone());
		personSummary.setTitle(person.getTitle());

		return personSummary;
	}

	/*
	 * Transfert une liste d'entité en liste d'objet de trasnfert résumé
	 * 
	 * @Param persons: Liste d'entité à mapper
	 * 
	 * @Return Retourne une liste d'objet de transfert résumé après le mappage
	 */
	public static List<PersonSummaryDTO> toDTOListSummary(List<Person> persons) {
		// Si l'entité d'entrée est null => on retroune null
		if (persons == null)
			return null;

		// Création de la liste d'objets de trasnfert résumé
		List<PersonSummaryDTO> summaries = new ArrayList<>();

		// Mappage de chaque élément de la liste
		for (Person person : persons) {
			summaries.add(PersonMapper.toSummaryDTO(person));
		}

		return summaries;
	}

	/*
	 * Transfert une entité en objet de transfert
	 * 
	 * @Param person: Entité à mapper
	 * 
	 * @Return Retourne l'objet de transfert après le mappage
	 */
	public static PersonDTO toDTO(Person person) {
		// Si l'entité d'entrée est null => on retroune null
		if (person == null)
			return null;

		// Création de l'objet de trasnfert
		PersonDTO personDTO = new PersonDTO();

		// Mappage des paramètres
		personDTO.setSummary(PersonMapper.toSummaryDTO(person));
		personDTO.setSubtitle(person.getSubtitle());
		personDTO.setPersonalValues(person.getPersonalValues());
		personDTO.setAddress(AddressMapper.toDTO(person.getAddress()));
		personDTO.setBirthDate(person.getBirthdate());
		// Mappage des sous-objets
		personDTO.setNationalities(NationalityMapper.toDTOList(person.getNationalities()));
		personDTO.setSpokenLanguages(SpokenLanguageMapper.toDTOList(person.getSpokenLanguages()));
		personDTO.setHobbies(HobbyMapper.toDTOList(person.getHobbies()));
		personDTO.setExperiences(ExperienceMapper.toSummaryDTOList(person.getExperiences()));

		return personDTO;
	}

	/*
	 * Transfert un objet de transfert en entité
	 * 
	 * @Param personDTO: Objet de transfert à mapper
	 * 
	 * @Return Retourne l'entité après le mappage
	 */
	public static Person toEntity(PersonDTO personDTO) {
		// Si l'objet de transfert d'entrée est null => on retroune null
		if (personDTO == null)
			return null;

		// Création de l'entité
		Person person = new Person(personDTO.getId());

		// Mappage des paramètres du résumé
		person.setName(personDTO.getSummary().getName());
		person.setFirstName(personDTO.getSummary().getFirstName());
		person.setMail(personDTO.getSummary().getMail());
		person.setPhone(personDTO.getSummary().getPhone());
		person.setTitle(personDTO.getSummary().getTitle());
		// Mappage des autres paramètres
		person.setSubtitle(personDTO.getSubtitle());
		person.setPersonalValues(personDTO.getPersonalValues());
		person.setBirthdate(personDTO.getBirthDate());
		person.setAddress(AddressMapper.toEntity(personDTO.getAddress()));
		// Mappage des sous-objets
		person.setNationalities(NationalityMapper.toEntityList(personDTO.getNationalities()));
		person.setSpokenLanguages(SpokenLanguageMapper.toEntityList(personDTO.getSpokenLanguages()));
		person.setHobbies(HobbyMapper.toEntityList(personDTO.getHobbies()));
		person.setExperiences(ExperienceMapper.toEntityListFromSummary(personDTO.getExperiences()));

		return person;
	}
}

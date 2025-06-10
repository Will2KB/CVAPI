package com.WB.API.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WB.API.dto.PersonDTO;
import com.WB.API.dto.PersonSummaryDTO;
import com.WB.API.exceptions.RessourceNotFoundException;
import com.WB.API.mapper.PersonMapper;
import com.WB.API.model.Person;
import com.WB.API.repository.PersonRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * Service permettant de manipuler l'objet personne
 */
@Service
@Slf4j
public class PersonService {

	@Autowired
	private PersonRepository personRepository;

	/**
	 * Calcule l'âge d'une personne en fonction de sa date de naissance enregistré
	 * dans l'entité
	 * 
	 * @param person: entité à partir de laquelle calculer l'age
	 * 
	 * @return Retourne un entier correspondant à l'âge de la personne en année
	 */
	public int getAge(Person person) {
		// Si la date de naissance n'est pas renseigné
		if (person.getBirthdate() == null)
			return 0;// On retourne 0

		// Sinon on retourne le résultat du calcul
		return Period.between(person.getBirthdate(), LocalDate.now()).getYears();
	}

	/**
	 * Récupère un résumé d'une personne à partir d'un ID donné
	 * 
	 * @param ID: id à rechercher
	 * 
	 * @return Retourne un objet de transfert résumé ou NULL si aucune personne
	 *         n'est trouvée
	 */
	public PersonSummaryDTO getPersonSummaryById(int id) {

		// Récherche en base de données
		Optional<Person> optPerson = personRepository.findById(id);

		// Si la recherche renvoie un résultat
		if (optPerson.isPresent()) {
			Person findPerson = optPerson.get();

			// Transfert du résultat sou forme d'objet de transfert
			PersonSummaryDTO findPersonDTO = PersonMapper.toSummaryDTO(findPerson);

			return findPersonDTO;
		} else {
			// Sinon on lève une exception
			throw new RessourceNotFoundException("Person not found with id: " + id);
		}
	}

	/**
	 * Récupère une personne à partir d'un ID donné
	 * 
	 * @param ID: id à rechercher
	 * 
	 * @return Retourne un objet de transfert ou NULL si aucune personne n'est
	 *         trouvée
	 */
	public PersonDTO getPersonById(int id) {
		// Récherche en base de données
		Optional<Person> optPerson = personRepository.findById(id);

		// Si la recherche renvoie un résultat
		if (optPerson.isPresent()) {
			Person findPerson = optPerson.get();
			// Transfert du résultat sou forme d'objet de transfert
			PersonDTO findPersonDTO = PersonMapper.toDTO(findPerson);

			// Si un objet a bien été trouvé
			if (findPersonDTO != null) {
				// On calcule l'âge de la personne
				findPersonDTO.setAge(this.getAge(findPerson));
			}

			return findPersonDTO;
		} else {
			// Sinon on lève une exception
			throw new RessourceNotFoundException("Person not found with id: " + id);
		}
	}

	/**
	 * Récupère une personne à partir d'un nom et un prénom donné
	 * 
	 * @param name:      nom à rechercher
	 * 
	 * @param firstName: prénom à rechercher
	 * 
	 * @return Retourne le premier élément trouvé en base de donné avec ce nom et ce
	 *         prénom sous forme d'objet de transfert ou NULL si aucune personne
	 *         n'est trouvée
	 */
	public PersonDTO getPersonByName(String name, String firstName) {
		Person findPerson = personRepository.findFirstPersonByNameAndFirstName(name, firstName);
		// Trasnfert du résultat en objet de transfert
		PersonDTO findPersonDTO = PersonMapper.toDTO(findPerson);

		// Si un objet a bien été trouvé
		if (findPersonDTO != null) {
			// On calcule l'âge de la personne
			findPersonDTO.setAge(this.getAge(findPerson));
		} else {
			// Sinon on lève une exception
			throw new RessourceNotFoundException(
					"Person not found with name : " + name + " and firstName : " + firstName);
		}

		return findPersonDTO;
	}

	/**
	 * Récupère une personne à partir d'un email donné
	 * 
	 * @param email: email à rechercher
	 * 
	 * @return Retourne le premier élément trouvé en base de donné avec cet email
	 *         sous forme d'objet de transfert ou NULL si aucune personne n'est
	 *         trouvée
	 */
	public PersonDTO getPersonByEmail(String email) {
		Person findPerson = personRepository.findFirstPersonByMail(email);
		// Transfert du résultat en objet de transfert
		PersonDTO findPersonDTO = PersonMapper.toDTO(findPerson);
		// Si un objet a bien été trouvé
		if (findPersonDTO != null) {
			// On calcule l'âge de la personne
			findPersonDTO.setAge(this.getAge(findPerson));
		} else {
			// Sinon on lève une exception
			throw new RessourceNotFoundException("Person not found with email: " + email);
		}
		return findPersonDTO;
	}

	/**
	 * Récupére la liste de toutes les personnes qui existent en base de donnée
	 * 
	 * @return Retourne une liste d'objet de transfert
	 */
	public List<PersonSummaryDTO> getPersons() {
		List<PersonSummaryDTO> summariesPersonDTO = PersonMapper.toDTOListSummary(personRepository.findAll());

		// Si aucune personne n'est trouvée
		if (summariesPersonDTO == null || summariesPersonDTO.isEmpty()) {
			// On lève une exception
			throw new RessourceNotFoundException("List of summaries of persons is empty.");
		}
		return summariesPersonDTO;
	}

	/**
	 * Enregistre une personne en base de donnée à partir d'un objet de transfert
	 * 
	 * @param person: l'objet de transfert reçu par l'API devant être enregistré en
	 *                base de données
	 * 
	 * @return Retourne le résumé de l'objet de transfert enregistré avec l'ID
	 *         enregistré en base de donnée
	 */
	public PersonSummaryDTO savePerson(PersonDTO person) {
		// Transfert de l'objet de transfert en entité de base de données
		Person toSave = PersonMapper.toEntity(person);
		return PersonMapper.toSummaryDTO(personRepository.save(toSave));
	}
}

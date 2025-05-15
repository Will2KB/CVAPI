package com.WB.API.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.WB.API.dto.PersonDTO;
import com.WB.API.service.PersonService;

/**
 * Controlleur mettant à disposition les différents chemin nécessaire pour
 * manipuler une personne
 */
@RestController
public class PersonController {

	@Autowired
	private PersonService personService;

//	/**
//	 * Récupére toutes les personnes
//	 *
//	 * @return Retourne une liste d'objet de transfert
//	 */
//	@GetMapping("/persons")
//	public List<PersonSummaryDTO> getPersons() {
//		return personService.getPersons();
//	}

	/**
	 * Récupère une personne à partir d'un ID
	 * 
	 * @param id: id à rechercher
	 * 
	 * @return Retourne un objet de transfert correspondant au résultat de la
	 *         recherche
	 */
	@GetMapping("/persons/id/{id}")
	public PersonDTO getPerson(@PathVariable int id) {
		return personService.getPersonById(id);
	}

	/**
	 * Récupère une personne à partir d'un nom et un prénom
	 * 
	 * @param name:      nom à rechercher
	 * 
	 * @param firstname: prénom à rechercher
	 * 
	 * @return Retourne un objet de transfert correspondant au résultat de la
	 *         recherche
	 */
	@GetMapping("/persons/name/{name}/firstname/{firstname}")
	public PersonDTO getPerson(@PathVariable String name, @PathVariable String firstname) {
		return personService.getPersonByName(name, firstname);
	}

	/**
	 * Récupère une personne à partir d'un email
	 * 
	 * @param email: email à rechercher
	 * 
	 * @return Retourne un objet de transfert correspondant au résultat de la
	 *         recherche
	 */
	@GetMapping("/persons/email/{eamil}")
	public PersonDTO getPerson(@PathVariable String email) {
		return personService.getPersonByEmail(email);
	}

//	/**
//	 * Sauvegarde d'une personne
//	 * 
//	 * @param person: Objet de transfert correspondant à la personne à enregsitrer
//	 * 
//	 * @return Retroune un résumé d'objet de transfert contenant l'identifiant de
//	 *         l'objet enregsitré
//	 */
//	@PostMapping("/persons")
//	public PersonSummaryDTO savePerson(@Valid @RequestBody PersonDTO person) {
//		return personService.savePerson(person);
//	}
}

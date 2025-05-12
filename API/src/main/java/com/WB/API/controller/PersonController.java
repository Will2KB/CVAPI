package com.WB.API.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.WB.API.dto.PersonDTO;
import com.WB.API.dto.PersonSummaryDTO;
import com.WB.API.service.PersonService;

import jakarta.validation.Valid;

/*
 * Controlleur mettant à disposition les différents chemin nécessaire pour manipuler une personne
 */
@RestController
public class PersonController {

	@Autowired
	private PersonService personService;

	/*
	 * Récupérer toutes les personnes
	 *
	 * @Return Retourne une liste d'objet de transfert
	 */
	@GetMapping("/persons")
	public List<PersonSummaryDTO> getPersons() {
		return personService.getPersons();
	}

	/*
	 * Récupèrer une personne à partir d'un ID
	 * 
	 * @Param id: id à rechercher
	 * 
	 * @Return Retourne un objet de transfert correspondant au résultat de la
	 * recherche
	 */
	@GetMapping("/persons/id/{id}")
	public PersonDTO getPerson(@PathVariable int id) {
		return personService.getPersonById(id);
	}

	/*
	 * Sauvegarde d'une personne
	 * 
	 * @Param person: Objet de transfert correspondant à la personne à enregsitrer
	 * 
	 * @Return Retroune un résumé d'objet de transfert contenant l'identifiant de
	 * l'objet enregsitré
	 */
	@PostMapping("/persons")
	public PersonSummaryDTO savePerson(@Valid @RequestBody PersonDTO person) {
		return personService.savePerson(person);
	}
}

package com.WB.API.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.WB.API.dto.NationalityDTO;
import com.WB.API.service.NationalityService;

/**
 * Controlleur mettant à disposition les différents chemin nécessaire pour
 * manipuler une nationalité
 */
@RestController
public class NationalityController {

	@Autowired
	private NationalityService nationalityService;

	/**
	 * Récupére toutes les nationalités
	 *
	 * @return Retourne une liste d'objet de transfert
	 */
	@GetMapping("/nationalities")
	public List<NationalityDTO> getNationalitys() {
		return nationalityService.getNationalities();
	}

	/**
	 * Récupère une nationalité à partir d'un ID
	 * 
	 * @param id: id à rechercher
	 * 
	 * @return Retourne un objet de transfert correspondant au résultat de la
	 *         recherche
	 */
	@GetMapping("/nationalities/id/{id}")
	public NationalityDTO getNationality(@PathVariable int id) {
		return nationalityService.getNationalityById(id);
	}
}

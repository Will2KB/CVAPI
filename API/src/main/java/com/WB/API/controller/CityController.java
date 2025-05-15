package com.WB.API.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.WB.API.dto.CityDTO;
import com.WB.API.service.CityService;

/**
 * Controlleur mettant à disposition les différents chemin nécessaire pour
 * manipuler une ville
 */
@RestController
public class CityController {

	@Autowired
	private CityService cityService;

	/**
	 * Récupére toutes les villes
	 *
	 * @return Retourne une liste d'objet de transfert
	 */
	@GetMapping("/cities")
	public List<CityDTO> getCities() {
		return cityService.getCities();
	}

	/**
	 * Récupère une ville à partir d'un ID
	 * 
	 * @param id: id à rechercher
	 * 
	 * @return Retourne un objet de transfert correspondant au résultat de la
	 *         recherche
	 */
	@GetMapping("/cities/id/{id}")
	public CityDTO getCity(@PathVariable int id) {
		return cityService.getCityByID(id);
	}

	/**
	 * Récupère une ville à partir d'un nom
	 * 
	 * @param name: nom à rechercher
	 * 
	 * @return Retourne un objet de trasnfert correspondant au résultat de la
	 *         recherche
	 */
	@GetMapping("/cities/name/{name}")
	public CityDTO getCity(@PathVariable String name) {
		return cityService.getCityByName(name);
	}
}

package com.WB.API.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.WB.API.dto.CountryDTO;
import com.WB.API.service.CountryService;

/*
 * Controlleur mettant à disposition les différents chemin nécessaire pour manipuler un pays
 */
@RestController
public class CountryController {

	@Autowired
	private CountryService countryService;

	/*
	 * Récupérer tous les pays
	 *
	 * @Return Retourn une liste d'objet de transfert
	 */
	@GetMapping("/countries")
	public List<CountryDTO> getCountrys() {
		return countryService.getCountries();
	}

	/*
	 * Récupèrer un pays à partir d'un ID
	 * 
	 * @Param id: id à rechercher
	 * 
	 * @Return Retourne un objet de trasnfert correspondant au résultat de la
	 * recherche
	 */
	@GetMapping("/countries/id/{id}")
	public CountryDTO getCountry(@PathVariable int id) {
		return countryService.getContryById(id);
	}

}

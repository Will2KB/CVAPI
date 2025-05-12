package com.WB.API.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.WB.API.dto.LanguageDTO;
import com.WB.API.service.LanguageService;

/*
 * Controlleur mettant à disposition les différents chemin nécessaire pour manipuler une langue
 */
@RestController
public class LanguageController {

	@Autowired
	private LanguageService languageService;

	/*
	 * Récupérer toutes les langues
	 *
	 * @Return Retourne une liste d'objet de transfert
	 */
	@GetMapping("/languages")
	public List<LanguageDTO> getLanguages() {
		return languageService.getLanguages();
	}

}

package com.WB.API.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.WB.API.dto.ExperienceDTO;
import com.WB.API.dto.ExperienceSummaryDTO;
import com.WB.API.service.ExperienceService;

/**
 * Controlleur mettant à disposition les différents chemin nécessaire pour
 * manipuler une expérience
 */
@RestController
public class ExperienceController {

	@Autowired
	private ExperienceService experienceService;

	/**
	 * Récupére toutes les expériences
	 *
	 * @return Retourne une liste d'objet de transfert
	 */
	@GetMapping("/experiences")
	public List<ExperienceSummaryDTO> getExperiences() {
		return experienceService.getExperiences();
	}

	/**
	 * Récupère une expérience à partir d'un ID
	 * 
	 * @param id: id à rechercher
	 * 
	 * @return Retourne un objet de transfert correspondant au résultat de la
	 *         recherche
	 */
	@GetMapping("/experiences/id/{id}")
	public ExperienceDTO getExperience(@PathVariable int id) {
		return experienceService.getExperienceById(id);
	}

//	/**
//	 * Sauvegarde d'une expérience
//	 * 
//	 * @param person: Objet de transfert correspondant à l'expérience à enregsitrer
//	 * 
//	 * @return Retroune un résumé d'objet de transfert contenant l'identifiant de
//	 *         l'objet enregsitré
//	 */
//	@PostMapping("/experiences")
//	public ExperienceSummaryDTO saveExperience(@Valid @RequestBody ExperienceDTO experience) {
//		return experienceService.saveExperience(experience);
//	}
}

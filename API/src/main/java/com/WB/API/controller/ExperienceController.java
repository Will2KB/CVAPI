package com.WB.API.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.WB.API.dto.ExperienceDTO;
import com.WB.API.dto.ExperienceSummaryDTO;
import com.WB.API.service.ExperienceService;

import jakarta.validation.Valid;

/*
 * Controlleur mettant à disposition les différents chemin nécessaire pour manipuler une expérience
 */
@RestController
public class ExperienceController {

	@Autowired
	private ExperienceService experienceService;

	/*
	 * Récupérer toutes les expériences
	 *
	 * @Return Retourne une liste d'objet de transfert
	 */
	@GetMapping("/experiences")
	public List<ExperienceSummaryDTO> getExperiences() {
		return experienceService.getExperiences();
	}

	/*
	 * Récupèrer une expérience à partir d'un ID
	 * 
	 * @Param id: id à rechercher
	 * 
	 * @Return Retourne un objet de transfert correspondant au résultat de la
	 * recherche
	 */
	@GetMapping("/experiences/id/{id}")
	public ResponseEntity<ExperienceDTO> getExperience(@PathVariable int id) {
		ExperienceDTO experience = experienceService.getExperienceById(id);
		if (experience == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(experience);
	}

	/*
	 * Sauvegarde d'une expérience
	 * 
	 * @Param person: Objet de transfert correspondant à l'expérience à enregsitrer
	 * 
	 * @Return Retroune un résumé d'objet de transfert contenant l'identifiant de
	 * l'objet enregsitré
	 */
	@PostMapping("/experiences")
	public ExperienceSummaryDTO saveExperience(@Valid @RequestBody ExperienceDTO experience) {
		return experienceService.saveExperience(experience);
	}
}

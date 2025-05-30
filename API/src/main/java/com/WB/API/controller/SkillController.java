package com.WB.API.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.WB.API.dto.SkillDTO;
import com.WB.API.service.SkillService;

/**
 * Controlleur mettant à disposition les différents chemin nécessaire pour
 * manipuler une compétence
 */
@RestController
public class SkillController {

	@Autowired
	private SkillService skillService;

	/**
	 * Récupére toutes les compétences
	 *
	 * @return Retourne une liste d'objet de transfert
	 */
	@GetMapping("/skills")
	public List<SkillDTO> getSkills() {
		return skillService.getSkills();
	}

	/**
	 * Récupère une compétence à partir d'un ID
	 * 
	 * @param id: id à rechercher
	 * 
	 * @return Retourne un objet de transfert correspondant au résultat de la
	 *         recherche
	 */
	@GetMapping("/skills/id/{id}")
	public SkillDTO getSkill(@PathVariable int id) {
		return skillService.getSkillByID(id);
	}
}

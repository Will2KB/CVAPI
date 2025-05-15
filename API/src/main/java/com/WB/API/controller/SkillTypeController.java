package com.WB.API.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.WB.API.dto.SkillTypeDTO;
import com.WB.API.service.SkillTypeService;

/**
 * Controlleur mettant à disposition les différents chemin nécessaire pour
 * manipuler un type de compétence
 */
@RestController
public class SkillTypeController {

	@Autowired
	private SkillTypeService skillTypeService;

	/**
	 * Récupére tous les type des compétences
	 *
	 * @return Retourne une liste d'objet de transfert
	 */
	@GetMapping("/skillTypes")
	public List<SkillTypeDTO> getSkillTypes() {
		return skillTypeService.getSkillTypes();
	}

	/**
	 * Récupère un type de compétence à partir d'un ID
	 * 
	 * @param id: id à rechercher
	 * 
	 * @return Retourne un objet de transfert correspondant au résultat de la
	 *         recherche
	 */
	@GetMapping("/skillTypes/id/{id}")
	public SkillTypeDTO getSkillType(@PathVariable int id) {
		return skillTypeService.getSkillTypeByID(id);
	}
}

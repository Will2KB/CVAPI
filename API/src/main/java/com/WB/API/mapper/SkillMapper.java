package com.WB.API.mapper;

import java.util.ArrayList;
import java.util.List;

import com.WB.API.dto.SkillDTO;
import com.WB.API.model.Skill;

/*
 * Classe permettant de mapper une entité en objet de trasnfert et inverssement
 * pour les Skill
 */
public class SkillMapper {

	/*
	 * Transfert une entité en objet de transfert
	 * 
	 * @Param skill: Entité à mapper
	 * 
	 * @Return Retourne l'objet de transfert après le mappage
	 */
	public static SkillDTO toDTO(Skill skill) {
		// Si l'entité d'entrée est null => on retroune null
		if (skill == null)
			return null;

		// Création de l'objet de transfert
		SkillDTO skillDTO = new SkillDTO();

		// Mappage des paramètres
		skillDTO.setId(skill.getId());
		skillDTO.setName(skill.getName());
		skillDTO.setEnable(skill.isEnable());
		// Mappage si un type existe
		if (skill.getType() != null) {
			skillDTO.setTypeId(skill.getType().getId());
			skillDTO.setTypeName(skill.getType().getName());
		}

		return skillDTO;
	}

	/*
	 * Transfert une liste d'entité en liste d'objet de trasnfert
	 * 
	 * @Param skills: Liste d'entité à mapper
	 * 
	 * @Return Retourne une liste d'objet de transfert après le mappage
	 */
	public static List<SkillDTO> toDTOList(List<Skill> skills) {
		// Si la liste est null => on retroune null
		if (skills == null)
			return null;

		// Craétion de la liste d'objets de transfert
		List<SkillDTO> skillsDTO = new ArrayList<>();

		// Mappage de chaque élément
		for (Skill skill : skills) {
			skillsDTO.add(SkillMapper.toDTO(skill));
		}

		return skillsDTO;
	}

	/*
	 * Transfert un objet de transfert en entité
	 * 
	 * @Param skillDTO: Objet de transfert à mapper
	 * 
	 * @Return Retourne l'entité après le mappage
	 */
	public static Skill toEntity(SkillDTO skillDTO) {
		// Si l'objet de transfert d'entrée est null => on retroune null
		if (skillDTO == null)
			return null;

		// Création de l'entité et mappage des paramètres
		Skill skill = new Skill(skillDTO.getId(), skillDTO.getTypeId());
		skill.setName(skillDTO.getName());
		skill.setEnable(skillDTO.isEnable());

		return skill;
	}

	/*
	 * Transfert une liste d'objet de transfert en liste d'entité
	 * 
	 * @Param skillsDTO: Liste d'objet de transfert à mapper
	 * 
	 * @Return Retourne une liste d'entité de transfert après le mappage
	 */
	public static List<Skill> toEntityList(List<SkillDTO> skillsDTO) {
		// Si la liste d'entrée est null => on retroune null
		if (skillsDTO == null)
			return null;

		// Création de la liste d'entités
		List<Skill> skills = new ArrayList<>();

		// Mappage de chaque élément
		for (SkillDTO skillDTO : skillsDTO) {
			skills.add(SkillMapper.toEntity(skillDTO));
		}

		return skills;
	}

}

package com.WB.API.mapper;

import java.util.ArrayList;
import java.util.List;

import com.WB.API.dto.SkillTypeDTO;
import com.WB.API.model.SkillType;

/**
 * Classe permettant de mapper une entité en objet de trasnfert et inverssement
 * pour les SkillType
 */
public class SkillTypeMapper {

	/**
	 * Transfert une entité en objet de transfert
	 * 
	 * @param skillType: Entité à mapper
	 * 
	 * @return Retourne l'objet de transfert après le mappage
	 */
	public static SkillTypeDTO toDTO(SkillType skillType) {
		// Si l'entité d'entrée est null => on retroune null
		if (skillType == null)
			return null;

		// Création de l'objet de transfert et mappage des paramètres
		SkillTypeDTO dto = new SkillTypeDTO(skillType.getId(), skillType.getName());

		return dto;
	}

	/**
	 * Transfert une liste d'entité en liste d'objet de trasnfert
	 * 
	 * @param skillTypes: Liste d'entité à mapper
	 * 
	 * @return Retourne une liste d'objet de transfert après le mappage
	 */
	public static List<SkillTypeDTO> toDTOList(List<SkillType> skillTypes) {
		// Si la liste d'entrée est null => on retroune null
		if (skillTypes == null)
			return null;

		// Création de la liste d'objet de transfert
		List<SkillTypeDTO> skillTypesDTO = new ArrayList<>();

		// Mappage de chaque élément de la liste
		for (SkillType skillType : skillTypes) {
			skillTypesDTO.add(SkillTypeMapper.toDTO(skillType));
		}

		return skillTypesDTO;
	}

	/**
	 * Transfert un objet de transfert en entité
	 * 
	 * @param dto: Objet de transfert à mapper
	 * 
	 * @return Retourne l'entité après le mappage
	 */
	public static SkillType toEntity(SkillTypeDTO dto) {
		// Si l'objet de transfert d'entrée est null => on retroune null
		if (dto == null)
			return null;

		// Création de l'entité + Mappage des paramètres
		SkillType skillType = new SkillType(dto.getId());
		skillType.setName(dto.getName());

		return skillType;
	}

	/**
	 * Transfert une liste d'objet de transfert en liste d'entité
	 * 
	 * @param skillTypesDTO: Liste d'objet de transfert à mapper
	 * 
	 * @return Retourne une liste d'entité de transfert après le mappage
	 */
	public static List<SkillType> toEntityList(List<SkillTypeDTO> skillTypesDTO) {
		// Si l'entité d'entrée est null => on retroune null
		if (skillTypesDTO == null)
			return null;

		// Création de la liste d'entités
		List<SkillType> skillTypes = new ArrayList<>();

		// Mappage de chaque élément de la liste
		for (SkillTypeDTO skillTypeDTO : skillTypesDTO) {
			skillTypes.add(SkillTypeMapper.toEntity(skillTypeDTO));
		}

		return skillTypes;
	}

}

package com.WB.API.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WB.API.dto.SkillTypeDTO;
import com.WB.API.exceptions.RessourceNotFoundException;
import com.WB.API.mapper.SkillTypeMapper;
import com.WB.API.model.SkillType;
import com.WB.API.repository.SkillTypeRepository;

/*
 * Service permettant de manipuler l'objet type de compétence
 */
@Service
public class SkillTypeService {

	@Autowired
	private SkillTypeRepository skillTypeRepository;

	/*
	 * Récupérer un type de compétence à partir d'un id donné
	 * 
	 * @Param ID: id à rechercher
	 * 
	 * @Retrun Retourne un objet de transfert ou NULL si aucun objet n'a été trouvé
	 */
	public SkillTypeDTO getSkillTypeByID(Integer ID) {
		// Récherche en base de données
		Optional<SkillType> optSkill = skillTypeRepository.findById(ID);

		// Si la recherche renvoie un résultat
		if (optSkill.isPresent()) {
			// On retourne l'objet sous forme d'objet de transfert
			return SkillTypeMapper.toDTO(optSkill.get());
		} else {
			// Sinon on lève une exception
			throw new RessourceNotFoundException("Skill type not found with id: " + ID);
		}
	}

	/*
	 * Récupérer la liste de tous les types de compétence qui existent en base de
	 * donnée
	 * 
	 * @Return Retourne une liste d'objet de transfert
	 */
	public List<SkillTypeDTO> getSkillTypes() {

		List<SkillTypeDTO> skillTypeDTO = SkillTypeMapper.toDTOList(skillTypeRepository.findAll());

		// Si aucune ville n'est trouvée
		if (skillTypeDTO == null || skillTypeDTO.isEmpty()) {
			// On lève une exception
			throw new RessourceNotFoundException("List of skill types is empty.");
		}
		return skillTypeDTO;
	}
}

package com.WB.API.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WB.API.dto.SkillDTO;
import com.WB.API.exceptions.RessourceNotFoundException;
import com.WB.API.mapper.SkillMapper;
import com.WB.API.model.Skill;
import com.WB.API.repository.SkillRepository;

/*
 * Service permettant de manipuler l'objet compétence
 */
@Service
public class SkillService {

	@Autowired
	private SkillRepository skillRepository;

	/*
	 * Récupérer une compétence à partir d'un id donné
	 * 
	 * @Param ID: id à rechercher
	 * 
	 * @Retrun Retourne un objet de transfert ou NULL si aucun objet n'a été trouvé
	 */
	public SkillDTO getSkillByID(Integer ID) {
		// Récherche en base de données
		Optional<Skill> optSkill = skillRepository.findById(ID);

		// Si la recherche renvoie un résultat
		if (optSkill.isPresent()) {
			return SkillMapper.toDTO(optSkill.get());
		} else {
			// Sinon on lève une exception
			throw new RessourceNotFoundException("Skill not found with id: " + ID);
		}
	}

	/*
	 * Récupérer la liste de tous les compétences qui existent en base de donnée
	 * 
	 * @Return Retourne une liste d'objet de transfert
	 */
	public List<SkillDTO> getSkills() {
		List<SkillDTO> skillsDTO = SkillMapper.toDTOList(skillRepository.findAll());

		// Si aucune ville n'est trouvée
		if (skillsDTO == null || skillsDTO.isEmpty()) {
			// On lève une exception
			throw new RessourceNotFoundException("List of skills is empty.");
		}
		return skillsDTO;

	}
}

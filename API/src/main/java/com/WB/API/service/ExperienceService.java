package com.WB.API.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WB.API.dto.ExperienceDTO;
import com.WB.API.dto.ExperienceSummaryDTO;
import com.WB.API.exceptions.RessourceNotFoundException;
import com.WB.API.mapper.ExperienceMapper;
import com.WB.API.model.Experience;
import com.WB.API.repository.ExperienceRepository;

/*
 * Service permettant de manipuler l'objet expérience
 */
@Service
public class ExperienceService {

	@Autowired
	private ExperienceRepository experienceRepository;

	/*
	 * Récupère une expérience à partir d'un ID donné
	 * 
	 * @Param ID: id à rechercher
	 * 
	 * @Retrun Retourne un objet de transfert ou NULL si aucune expérience n'est
	 * trouvée
	 */
	public ExperienceDTO getExperienceById(int id) {
		// Récherche en base de données
		Optional<Experience> optExperience = experienceRepository.findById(id);

		// Si la recherche renvoie un résultat
		if (optExperience.isPresent()) {
			return ExperienceMapper.toDTO(optExperience.get());
		} else {
			// Sinon on lève une exception
			throw new RessourceNotFoundException("Experience not found with id: " + id);
		}
	}

	/*
	 * Récupérer la liste de toutes les expériences qui existent en base de donnée
	 * 
	 * @Return Retourne une liste d'objet de transfert
	 */
	public List<ExperienceSummaryDTO> getExperiences() {
		List<ExperienceSummaryDTO> exprienceSummariesDTO = ExperienceMapper.toSummaryDTOList(experienceRepository.findAll());

		// Si aucune ville n'est trouvée
		if (exprienceSummariesDTO == null || exprienceSummariesDTO.isEmpty()) {
			// On lève une exception
			throw new RessourceNotFoundException("List of summaries of experience is empty.");
		}
		return exprienceSummariesDTO;
	}

	/*
	 * Enregistre une expérience en base de donnée à partir d'un objet de transfert
	 * 
	 * @Param experience: l'objet de transfert reçu par l'API devant être enregistré
	 * en base de données
	 * 
	 * @Retrun Retourne le résumé de l'objet de transfert enregistré avec l'ID
	 * enregistré en base de donnée
	 */
	public ExperienceSummaryDTO saveExperience(ExperienceDTO experience) {
		// Transfert de l'objet de transfert en entité de base de données
		Experience toSave = ExperienceMapper.toEntity(experience);
		return ExperienceMapper.toSummaryDTO(experienceRepository.save(toSave));
	}

}

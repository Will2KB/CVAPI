package com.WB.API.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WB.API.dto.NationalityDTO;
import com.WB.API.exceptions.RessourceNotFoundException;
import com.WB.API.mapper.NationalityMapper;
import com.WB.API.model.Nationality;
import com.WB.API.repository.NationalityRepository;

/*
 * Service permettant de manipuler l'objet nationalité
 */
@Service
public class NationalityService {

	@Autowired
	private NationalityRepository nationalityRepository;

	/*
	 * Récupère une nationalité à partir d'un ID donné
	 * 
	 * @Param ID: id à rechercher
	 * 
	 * @Retrun Retourne un objet de transfert ou NULL si aucune nationalité n'est
	 * trouvée
	 */
	public NationalityDTO getNationalityById(int id) {
		// Récherche en base de données
		Optional<Nationality> optNationality = nationalityRepository.findById(id);

		// Si la recherche renvoie un résultat
		if (optNationality.isPresent()) {
			return NationalityMapper.toDTO(optNationality.get());
		} else {
			// Sinon on lève une exception
			throw new RessourceNotFoundException("Nationality not found with id: " + id);
		}
	}

	/*
	 * Récupérer la liste de toutes les nationalités qui existent en base de donnée
	 * 
	 * @Return Retourne une liste d'objet de transfert
	 */
	public List<NationalityDTO> getNationalities() {
		List<NationalityDTO> nationalitiesDTO = NationalityMapper.toDTOList(nationalityRepository.findAll());

		// Si aucune ville n'est trouvée
		if (nationalitiesDTO == null || nationalitiesDTO.isEmpty()) {
			// On lève une exception
			throw new RessourceNotFoundException("List of nationalities is empty.");
		}
		return nationalitiesDTO;
	}
}

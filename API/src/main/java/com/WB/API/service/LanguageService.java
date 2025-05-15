package com.WB.API.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WB.API.dto.LanguageDTO;
import com.WB.API.exceptions.RessourceNotFoundException;
import com.WB.API.mapper.LanguageMapper;
import com.WB.API.model.Language;
import com.WB.API.repository.LanguageRepository;

/**
 * Service permettant de manipuler l'objet language
 */
@Service
public class LanguageService {

	@Autowired
	private LanguageRepository languageRepository;

	/**
	 * Récupére la liste de toutes les langues qui existent en base de donnée
	 * 
	 * @return Retourne une liste d'objet de transfert
	 */
	public List<LanguageDTO> getLanguages() {
		List<LanguageDTO> languagesDTO = LanguageMapper.toDTOList(languageRepository.findAll());

		// Si aucune langue n'est trouvée
		if (languagesDTO == null || languagesDTO.isEmpty()) {
			// On lève une exception
			throw new RessourceNotFoundException("List of languages is empty.");
		}
		return languagesDTO;
	}

	/**
	 * Récupère une langue à partir d'un ID donné
	 * 
	 * @param ID: id à rechercher
	 * 
	 * @return Retourne un objet de transfert ou NULL si aucune ville n'est trouvée
	 */
	public LanguageDTO getLanguageById(int id) {
		// Récherche en base de données
		Optional<Language> optLanguage = languageRepository.findById(id);

		// Si la recherche renvoie un résultat
		if (optLanguage.isPresent()) {
			return LanguageMapper.toDTO(optLanguage.get());
		} else {
			// Sinon on lève une exception
			throw new RessourceNotFoundException("Language not found with id: " + id);
		}
	}

}

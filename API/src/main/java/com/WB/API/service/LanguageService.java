package com.WB.API.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WB.API.dto.LanguageDTO;
import com.WB.API.exceptions.RessourceNotFoundException;
import com.WB.API.mapper.LanguageMapper;
import com.WB.API.repository.LanguageRepository;

/*
 * Service permettant de manipuler l'objet language
 */
@Service
public class LanguageService {

	@Autowired
	private LanguageRepository languageRepository;

	/*
	 * Récupérer la liste de toutes les langues qui existent en base de donnée
	 * 
	 * @Return Retourne une liste d'objet de transfert
	 */
	public List<LanguageDTO> getLanguages() {
		List<LanguageDTO> languagesDTO = LanguageMapper.toDTOList(languageRepository.findAll());

		// Si aucune ville n'est trouvée
		if (languagesDTO == null || languagesDTO.isEmpty()) {
			// On lève une exception
			throw new RessourceNotFoundException("List of languages is empty.");
		}
		return languagesDTO;
	}
}

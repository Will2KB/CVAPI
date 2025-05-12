package com.WB.API.mapper;

import java.util.ArrayList;
import java.util.List;

import com.WB.API.dto.SpokenLanguageDTO;
import com.WB.API.model.SpokenLanguage;

/*
 * Classe permettant de mapper une entité en objet de trasnfert et inverssement
 * pour les SpokenLanguage
 */
public class SpokenLanguageMapper {

	/*
	 * Transfert une entité en objet de transfert
	 * 
	 * @Param language: Entité à mapper
	 * 
	 * @Return Retourne l'objet de transfert après le mappage
	 */
	public static SpokenLanguageDTO toDTO(SpokenLanguage language) {
		// Si l'entité d'entrée est null => on retroune null
		if (language == null)
			return null;

		// Crèation de l'objet de transfert
		SpokenLanguageDTO languageDTO = new SpokenLanguageDTO();

		// Mappage des paramètres
		languageDTO.setLanguageId(language.getLanguage().getId());
		languageDTO.setName(language.getLanguage().getName());
		languageDTO.setPersonId(language.getPerson().getId());
		languageDTO.setLevel(language.getLevel());

		return languageDTO;
	}

	/*
	 * Transfert une liste d'entité en liste d'objet de trasnfert
	 * 
	 * @Param languages: Liste d'entité à mapper
	 * 
	 * @Return Retourne une liste d'objet de transfert après le mappage
	 */
	public static List<SpokenLanguageDTO> toDTOList(List<SpokenLanguage> languages) {

		// Si la liste d'entrée est null => on retroune null
		if (languages == null)
			return null;

		// Création de la liste d'objet de transfert
		List<SpokenLanguageDTO> languagesDTO = new ArrayList<>();

		// Mappage de chaque élément de la liste
		for (SpokenLanguage spokenLanguage : languages) {
			languagesDTO.add(SpokenLanguageMapper.toDTO(spokenLanguage));
		}

		return languagesDTO;
	}

	/*
	 * Transfert un objet de transfert en entité
	 * 
	 * @Param languageDTO: Objet de transfert à mapper
	 * 
	 * @Return Retourne l'entité après le mappage
	 */
	public static SpokenLanguage toEntity(SpokenLanguageDTO languageDTO) {
		// Si l'objet de transfert d'entrée est null => on retroune null
		if (languageDTO == null)
			return null;

		// Création de l'entité et mappage des paramètres
		SpokenLanguage language = new SpokenLanguage(languageDTO.getPersonId(), languageDTO.getLanguageId());
		language.setLevel(languageDTO.getLevel());

		return language;
	}

	/*
	 * Transfert une liste d'objet de transfert en liste d'entité
	 * 
	 * @Param languagesDTO: Liste d'objet de transfert à mapper
	 * 
	 * @Return Retourne une liste d'entité après le mappage
	 */
	public static List<SpokenLanguage> toEntityList(List<SpokenLanguageDTO> languagesDTO) {
		// Si la liste d'entrée est null => on retroune null
		if (languagesDTO == null)
			return null;

		// Craétion de la liste d'entité
		List<SpokenLanguage> languages = new ArrayList<>();

		// Mappage de chaque élément de la liste
		for (SpokenLanguageDTO spokenLanguageDTO : languagesDTO) {
			languages.add(SpokenLanguageMapper.toEntity(spokenLanguageDTO));
		}

		return languages;
	}
}

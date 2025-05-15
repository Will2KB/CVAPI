package com.WB.API.mapper;

import java.util.ArrayList;
import java.util.List;

import com.WB.API.dto.LanguageDTO;
import com.WB.API.model.Language;

/**
 * Classe permettant de mapper une entité en objet de trasnfert et inverssement
 * pour les Language
 */
public class LanguageMapper {

	/**
	 * Transfert une entité en objet de transfert
	 * 
	 * @param language: Entité à mapper
	 * 
	 * @return Retourne l'objet de transfert après le mappage
	 */
	public static LanguageDTO toDTO(Language language) {
		// Si l'entité d'entrée est null => on retroune null
		if (language == null)
			return null;

		// Création de l'objet de transfert
		LanguageDTO languageDTO = new LanguageDTO();

		// Mappage des paramètres
		languageDTO.setId(language.getId());
		languageDTO.setName(language.getName());

		return languageDTO;
	}

	/**
	 * Transfert une liste d'entité en liste d'objet de trasnfert
	 * 
	 * @param languages: Liste d'entité à mapper
	 * 
	 * @return Retourne une liste d'objet de transfert après le mappage
	 */
	public static List<LanguageDTO> toDTOList(List<Language> languages) {
		// Si la liste d'entrée est null => on retroune null
		if (languages == null)
			return null;

		// Création de la liste d'objets de trasnfert
		List<LanguageDTO> languagesDTO = new ArrayList<>();

		// Mappage de chaque élément de la liste
		for (Language Language : languages) {
			languagesDTO.add(LanguageMapper.toDTO(Language));
		}

		return languagesDTO;
	}

	/**
	 * Transfert un objet de transfert en entité
	 * 
	 * @param languageDTO: Objet de transfert à mapper
	 * 
	 * @return Retourne l'entité après le mappage
	 */
	public static Language toEntity(LanguageDTO languageDTO) {
		// Si l'objet de transfert d'entrée est null => on retroune null
		if (languageDTO == null)
			return null;

		// Création de l'entité et mappage des paramètres
		Language Language = new Language(languageDTO.getId(), languageDTO.getName());

		return Language;
	}

	/**
	 * Transfert une liste d'objet de transfert en liste d'entité
	 * 
	 * @param languagesDTO: Liste d'objet de transfert à mapper
	 * 
	 * @return Retourne une liste d'entité de transfert après le mappage
	 */
	public static List<Language> toEntityList(List<LanguageDTO> languagesDTO) {
		// Si la liste d'entrée est null => on retroune null
		if (languagesDTO == null)
			return null;

		// Création de la liste d'entités
		List<Language> languages = new ArrayList<>();

		// Mappage de chaque élément de la liste
		for (LanguageDTO languageDTO : languagesDTO) {
			languages.add(LanguageMapper.toEntity(languageDTO));
		}

		return languages;
	}

}

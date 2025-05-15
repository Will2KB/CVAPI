package com.WB.API.mapper;

import java.util.ArrayList;
import java.util.List;

import com.WB.API.dto.HobbyDTO;
import com.WB.API.model.Hobby;

/**
 * Classe permettant de mapper une entité en objet de trasnfert et inverssement
 * pour les Hobby
 */
public class HobbyMapper {

	/**
	 * Transfert une entité en objet de transfert
	 * 
	 * @param hobby: Entité à mapper
	 * 
	 * @return Retourne l'objet de transfert après le mappage
	 */
	public static HobbyDTO toDTO(Hobby hobby) {
		// Si l'entité d'entrée est null => on retroune null
		if (hobby == null)
			return null;

		// Création de l'objet de transfert
		HobbyDTO hobbyDTO = new HobbyDTO();

		// Mappage des pramaètres
		hobbyDTO.setId(hobby.getId());
		hobbyDTO.setName(hobby.getName());
		hobbyDTO.setDescriptions(HobbyDescriptionMapper.toDTOList(hobby.getDescriptions()));

		return hobbyDTO;
	}

	/**
	 * Transfert une liste d'entité en liste d'objet de trasnfert
	 * 
	 * @param hobbies: Liste d'entité à mapper
	 * 
	 * @return Retourne une liste d'objet de transfert après le mappage
	 */
	public static List<HobbyDTO> toDTOList(List<Hobby> hobbies) {
		// Si la liste d'entrée est null => on retroune null
		if (hobbies == null)
			return null;

		// Création de la liste d'objets de transfert
		List<HobbyDTO> hobbiesDTO = new ArrayList<>();

		// Mappage pour chaque élément de la liste
		for (Hobby hobby : hobbies) {
			hobbiesDTO.add(HobbyMapper.toDTO(hobby));
		}
		return hobbiesDTO;
	}

	/**
	 * Transfert un objet de transfert en entité
	 * 
	 * @param hobbyDTO: Objet de transfert à mapper
	 * 
	 * @return Retourne l'entité après le mappage
	 */
	public static Hobby toEntity(HobbyDTO hobbyDTO) {
		// Si l'objet de transfert d'entrée est null => on retroune null
		if (hobbyDTO == null)
			return null;

		// Création de l'entité
		Hobby hobby = new Hobby(hobbyDTO.getId());

		// Mappage des paramètres
		hobby.setName(hobbyDTO.getName());
		hobby.setDescriptions(HobbyDescriptionMapper.toEntityList(hobbyDTO.getDescriptions()));

		return hobby;
	}

	/**
	 * Transfert une liste d'objet de transfert en liste d'entité
	 * 
	 * @param hobbiesDTO: Liste d'objet de transfert à mapper
	 * 
	 * @return Retourne une liste d'entité de transfert après le mappage
	 */
	public static List<Hobby> toEntityList(List<HobbyDTO> hobbiesDTO) {
		// Si la liste d'entrée est null => on retroune null
		if (hobbiesDTO == null)
			return null;

		// Création de la liste d'entités
		List<Hobby> hobbies = new ArrayList<>();

		// Mappage de chque élément de la liste
		for (HobbyDTO hobbyDTO : hobbiesDTO) {
			hobbies.add(HobbyMapper.toEntity(hobbyDTO));
		}

		return hobbies;
	}
}

package com.WB.API.mapper;

import java.util.ArrayList;
import java.util.List;

import com.WB.API.dto.HobbyDescriptionDTO;
import com.WB.API.model.HobbyDescription;

/*
 * Classe permettant de mapper une entité en objet de trasnfert et inverssement
 * pour les HobbyDescription
 */
public class HobbyDescriptionMapper {

	/*
	 * Transfert une entité en objet de transfert
	 * 
	 * @Param hobbyDescription: Entité à mapper
	 * 
	 * @Return Retourne l'objet de transfert après le mappage
	 */
	public static HobbyDescriptionDTO toDTO(HobbyDescription hobbyDescription) {
		// Si l'entité d'entrée est null => on retroune null
		if (hobbyDescription == null)
			return null;

		// Création de l'objet de trasnfert
		HobbyDescriptionDTO hobbyDescriptionDTO = new HobbyDescriptionDTO();

		// Mappage des paramètres
		hobbyDescriptionDTO.setId(hobbyDescription.getId());
		hobbyDescriptionDTO.setDescription(hobbyDescription.getDescription());

		return hobbyDescriptionDTO;
	}

	/*
	 * Transfert une liste d'entité en liste d'objet de trasnfert
	 * 
	 * @Param hobbyDescriptions: Liste d'entité à mapper
	 * 
	 * @Return Retourne une liste d'objet de transfert après le mappage
	 */
	public static List<HobbyDescriptionDTO> toDTOList(List<HobbyDescription> hobbyDescriptions) {
		// Si la liste d'entrée est null => on retroune null
		if (hobbyDescriptions == null)
			return null;

		// Création de la liste d'objets de trasnfert
		List<HobbyDescriptionDTO> hobbyDescriptionsDTO = new ArrayList<>();

		// Mappage de chaque élément de la liste
		for (HobbyDescription hobbyDescription : hobbyDescriptions) {
			hobbyDescriptionsDTO.add(HobbyDescriptionMapper.toDTO(hobbyDescription));
		}

		return hobbyDescriptionsDTO;
	}

	/*
	 * Transfert un objet de transfert en entité
	 * 
	 * @Param hobbyDescriptionDTO: Objet de transfert à mapper
	 * 
	 * @Return Retourne l'entité après le mappage
	 */
	public static HobbyDescription toEntity(HobbyDescriptionDTO hobbyDescriptionDTO) {
		// Si l'objket de trasnfert d'entrée est null => on retroune null
		if (hobbyDescriptionDTO == null)
			return null;

		// Création de l'entité et mappage des paramètres
		HobbyDescription hobbyDescription = new HobbyDescription(hobbyDescriptionDTO.getId(),
				hobbyDescriptionDTO.getDescription());

		return hobbyDescription;
	}

	/*
	 * Transfert une liste d'objet de transfert en liste d'entité
	 * 
	 * @Param hobbyDescriptionsDTO: Liste d'objet de transfert à mapper
	 * 
	 * @Return Retourne une liste d'entité de transfert après le mappage
	 */
	public static List<HobbyDescription> toEntityList(List<HobbyDescriptionDTO> hobbyDescriptionsDTO) {
		// Si l'entité d'entrée est null => on retroune null
		if (hobbyDescriptionsDTO == null)
			return null;

		// Création de la liste d'entités
		List<HobbyDescription> hobbyDescriptions = new ArrayList<>();

		// Mappage de chque élément de la liste
		for (HobbyDescriptionDTO hobbyDescriptionDTO : hobbyDescriptionsDTO) {
			hobbyDescriptions.add(toEntity(hobbyDescriptionDTO));
		}

		return hobbyDescriptions;
	}
}

package com.WB.API.mapper;

import java.util.ArrayList;
import java.util.List;

import com.WB.API.dto.NationalityDTO;
import com.WB.API.model.Nationality;

/*
 * Classe permettant de mapper une entité en objet de trasnfert et inverssement
 * pour les Nationality
 */
public class NationalityMapper {

	/*
	 * Transfert une entité en objet de transfert
	 * 
	 * @Param nationality: Entité à mapper
	 * 
	 * @Return Retourne l'objet de transfert après le mappage
	 */
	public static NationalityDTO toDTO(Nationality nationality) {
		// Si l'entité d'entrée est null => on retroune null
		if (nationality == null)
			return null;

		// Création de l'objet de transfert
		NationalityDTO nationalityDTO = new NationalityDTO();

		// Mappage des paramètres
		nationalityDTO.setId(nationality.getId());
		nationalityDTO.setName(nationality.getName());

		return nationalityDTO;
	}

	/*
	 * Transfert une liste d'entité en liste d'objet de trasnfert
	 * 
	 * @Param nationalities: Liste d'entité à mapper
	 * 
	 * @Return Retourne une liste d'objet de transfert après le mappage
	 */
	public static List<NationalityDTO> toDTOList(List<Nationality> nationalities) {
		// Si la liste d'entrée est null => on retroune null
		if (nationalities == null)
			return null;

		// Création de la liste d'objet de transfert
		List<NationalityDTO> nationalitiesDTO = new ArrayList<>();

		// Mappage de chaque élément de la liste
		for (Nationality nationality : nationalities) {
			nationalitiesDTO.add(NationalityMapper.toDTO(nationality));
		}

		return nationalitiesDTO;
	}

	/*
	 * Transfert un objet de transfert en entité
	 * 
	 * @Param nationalityDTO: Objet de transfert à mapper
	 * 
	 * @Return Retourne l'entité après le mappage
	 */
	public static Nationality toEntity(NationalityDTO nationalityDTO) {
		// Si l'objet de transfert d'entrée est null => on retroune null
		if (nationalityDTO == null)
			return null;

		// Création de l'entité et mappage des paramètres
		Nationality nationality = new Nationality(nationalityDTO.getId(), nationalityDTO.getName());

		return nationality;
	}

	/*
	 * Transfert une liste d'objet de transfert en liste d'entité
	 * 
	 * @Param nationalitiesDTO: Liste d'objet de transfert à mapper
	 * 
	 * @Return Retourne une liste d'entité de transfert après le mappage
	 */
	public static List<Nationality> toEntityList(List<NationalityDTO> nationalitiesDTO) {
		// Si l'objet de transfert d'entrée est null => on retroune null
		if (nationalitiesDTO == null)
			return null;

		// Création de la liste d'entités
		List<Nationality> nationalities = new ArrayList<>();

		// Mappage de chaque élément de la liste
		for (NationalityDTO nationalityDTO : nationalitiesDTO) {
			nationalities.add(NationalityMapper.toEntity(nationalityDTO));
		}

		return nationalities;
	}

}

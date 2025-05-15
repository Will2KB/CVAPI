package com.WB.API.mapper;

import java.util.ArrayList;
import java.util.List;

import com.WB.API.dto.CountryDTO;
import com.WB.API.model.Country;

/**
 * Classe permettant de mapper une entité en objet de trasnfert et inverssement
 * pour les Country
 */
public class CountryMapper {

	/**
	 * Transfert une entité en objet de transfert
	 * 
	 * @param country: Entité à mapper
	 * 
	 * @return Retourne l'objet de transfert après le mappage
	 */
	public static CountryDTO toDTO(Country country) {
		// Si l'entité d'entrée est null => on retroune null
		if (country == null)
			return null;

		// Création de l'objet de transfert
		CountryDTO countryDTO = new CountryDTO();

		// Mappage des paramètres
		countryDTO.setId(country.getId());
		countryDTO.setName(country.getName());

		return countryDTO;
	}

	/**
	 * Transfert une liste d'entité en liste d'objet de trasnfert
	 * 
	 * @param countries: Liste d'entité à mapper
	 * 
	 * @return Retourne une liste d'objet de transfert après le mappage
	 */
	public static List<CountryDTO> toDTOList(List<Country> countries) {
		// Si la liste d'entrée est null => on retroune null
		if (countries == null)
			return null;

		// Création de la liste d'objets de transfert
		List<CountryDTO> countriesDTO = new ArrayList<>();

		// Mappage de chaque élément de la liste
		for (Country Country : countries) {
			countriesDTO.add(CountryMapper.toDTO(Country));
		}

		return countriesDTO;
	}

	/**
	 * Transfert un objet de transfert en entité
	 * 
	 * @param countryDTO: Objet de transfert à mapper
	 * 
	 * @return Retourne l'entité après le mappage
	 */
	public static Country toEntity(CountryDTO countryDTO) {
		// Si l'objet de trasnfert d'entrée est null => on retroune null
		if (countryDTO == null)
			return null;

		// Création de l'entité et mappage des paramètres
		Country country = new Country(countryDTO.getId(), countryDTO.getName());
		return country;
	}

	/**
	 * Transfert une liste d'objet de transfert en liste d'entité
	 * 
	 * @param countriesDTO: Liste d'objet de transfert à mapper
	 * 
	 * @return Retourne une liste d'entité de transfert après le mappage
	 */
	public static List<Country> toEntityList(List<CountryDTO> countriesDTO) {
		// Si la liste d'entrée est null => on retroune null
		if (countriesDTO == null)
			return null;

		// Création de la liste d'entités
		List<Country> countries = new ArrayList<>();

		// Mappage de chaque élément de la liste
		for (CountryDTO countryDTO : countriesDTO) {
			countries.add(CountryMapper.toEntity(countryDTO));
		}

		return countries;
	}

}

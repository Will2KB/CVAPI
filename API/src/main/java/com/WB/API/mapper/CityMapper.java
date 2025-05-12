package com.WB.API.mapper;

import java.util.ArrayList;
import java.util.List;

import com.WB.API.dto.CityDTO;
import com.WB.API.model.City;
import com.WB.API.model.Country;

/*
 * Classe permettant de mapper une entité en objet de trasnfert et inverssement
 * pour les City
 */
public class CityMapper {

	/*
	 * Transfert une entité en objet de transfert
	 * 
	 * @Param city: Entité à mapper
	 * 
	 * @Return Retourne l'objet de transfert après le mappage
	 */
	public static CityDTO toDTO(City city) {
		// Si l'entité d'entrée est null => on retroune null
		if (city == null)
			return null;

		// Création de l'objet de transfert
		CityDTO dto = new CityDTO();

		// Mappage des paramètres
		dto.setId(city.getId());
		dto.setName(city.getName());
		dto.setZipCode(city.getZipCode());
		// Mappage si il y a un pays
		if (city.getCountry() != null) {
			dto.setCountryId(city.getCountry().getId());
			dto.setCountryName(city.getCountry().getName());
		}
		return dto;
	}

	/*
	 * Transfert une liste d'entité en liste d'objet de trasnfert
	 * 
	 * @Param cities: Liste d'entité à mapper
	 * 
	 * @Return Retourne une liste d'objet de transfert après le mappage
	 */
	public static List<CityDTO> toDTOList(List<City> cities) {
		// Si la liste d'entrée est null => on retroune null
		if (cities == null)
			return null;

		// Création de la liste d'objet de transfert
		List<CityDTO> citiesDTO = new ArrayList<>();

		// Mappage de chaque élément de la liste
		for (City city : cities) {
			citiesDTO.add(CityMapper.toDTO(city));
		}

		return citiesDTO;
	}

	/*
	 * Transfert un objet de transfert en entité
	 * 
	 * @Param cityDTO: Objet de transfert à mapper
	 * 
	 * @Return Retourne l'entité après le mappage
	 */
	public static City toEntity(CityDTO cityDTO) {
		// Si l'objet de transfert d'entrée est null => on retroune null
		if (cityDTO == null)
			return null;

		// Création de l'entité + Mappage de certains paramètres
		City city = new City(cityDTO.getId(), cityDTO.getName(), cityDTO.getZipCode());

		// Mappage des paramètres du pays
		Country country = new Country(cityDTO.getCountryId(), cityDTO.getCountryName());
		city.setCountry(country);

		return city;
	}

}

package com.WB.API.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WB.API.dto.CityDTO;
import com.WB.API.exceptions.RessourceNotFoundException;
import com.WB.API.mapper.CityMapper;
import com.WB.API.model.City;
import com.WB.API.repository.CityRepository;

/*
 * Service permettant de manipuler l'objet Ville
 */
@Service
public class CityService {

	@Autowired
	private CityRepository cityRepository;

	/*
	 * Récupère une ville à partir d'un ID donné
	 * 
	 * @Param ID: id à rechercher
	 * 
	 * @Retrun Retourne un objet de transfert ou NULL si aucune ville n'est trouvée
	 */
	public CityDTO getCityByID(Integer ID) {
		// Récherche en base de données
		Optional<City> optCity = cityRepository.findById(ID);

		// Si la recherche renvoie un résultat
		if (optCity.isPresent()) {
			return CityMapper.toDTO(optCity.get());
		} else {
			// Sinon on lève une exception
			throw new RessourceNotFoundException("City not found with id: " + ID);
		}
	}

	/*
	 * Récupère une ville à partir d'un nom donné
	 * 
	 * @Param name: nom à rechercher
	 * 
	 * @Retrun Retourne le premier élément trouvé en base de donné avec ce nom sous
	 * forme d'objet de transfert ou NULL si aucune ville n'est trouvée
	 */
	public CityDTO getCityByName(String name) {
		CityDTO cityDTO = CityMapper.toDTO(cityRepository.findFirstCityByName(name));

		// Si aucune ville n'est trouvée
		if (cityDTO == null) {
			// On lève une exception
			throw new RessourceNotFoundException("City not found with name: " + name);
		}

		return cityDTO;
	}

	/*
	 * Récupérer la liste de toutes les villes qui existent en base de donnée
	 * 
	 * @Return Retourne une liste d'objet de transfert
	 */
	public List<CityDTO> getCities() {

		List<CityDTO> citiesDTO = CityMapper.toDTOList(cityRepository.findAll());

		// Si aucune ville n'est trouvée
		if (citiesDTO == null || citiesDTO.isEmpty()) {
			// On lève une exception
			throw new RessourceNotFoundException("List of cities is empty.");
		}
		return citiesDTO;
	}
}

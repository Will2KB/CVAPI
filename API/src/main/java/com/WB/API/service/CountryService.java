package com.WB.API.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WB.API.dto.CountryDTO;
import com.WB.API.exceptions.RessourceNotFoundException;
import com.WB.API.mapper.CountryMapper;
import com.WB.API.model.Country;
import com.WB.API.repository.CountryRepository;

/*
 * Service permettant de manipuler l'objet pays
 */
@Service
public class CountryService {

	@Autowired
	private CountryRepository countryRepository;

	/*
	 * Récupère un pays à partir d'un ID donné
	 * 
	 * @Param ID: id à rechercher
	 * 
	 * @Retrun Retourne un objet de transfert ou NULL si aucun pays n'est trouvé
	 */
	public CountryDTO getContryById(Integer ID) {
		// Récherche en base de données
		Optional<Country> optCountry = countryRepository.findById(ID);

		// Si la recherche renvoie un résultat
		if (optCountry.isPresent()) {
			return CountryMapper.toDTO(optCountry.get());
		} else {
			// Sinon on lève une exception
			throw new RessourceNotFoundException("Country not found with id: " + ID);
		}
	}

	/*
	 * Récupérer la liste de tous les pays qui existent en base de donnée
	 * 
	 * @Return Retourne une liste d'objet de transfert
	 */
	public List<CountryDTO> getCountries() {
		List<CountryDTO> countriesDTO = CountryMapper.toDTOList(countryRepository.findAll());

		// Si aucune ville n'est trouvée
		if (countriesDTO == null || countriesDTO.isEmpty()) {
			// On lève une exception
			throw new RessourceNotFoundException("List of countries is empty.");
		}
		return countriesDTO;
	}
}

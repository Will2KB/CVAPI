package com.WB.API.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.WB.API.model.City;

/**
 * Repository permettant de manipuler une ville dans la base de données
 */
@Repository
public interface CityRepository extends CrudRepository<City, Integer> {

	/**
	 * Récupére la première occurance trouvée correspondant au nom
	 * 
	 * @param name : Nom à rechercher
	 * 
	 * @return Retourne une entité ville ou NULL si aucune occurance n'est trouvée
	 */
	public City findFirstCityByName(String name);

	/**
	 * Récupére la liste de toutes les villes
	 * 
	 * @Return Retourne une liste d'entité ville
	 */
	public List<City> findAll();

}

package com.WB.API.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.WB.API.model.City;

/*
 * Repository permettant de manipuler une ville dans la base de données
 */
@Repository
public interface CityRepository extends CrudRepository<City, Integer> {

	public City findFirstCityByName(String name);

	/*
	 * Permet de récupérer la liste de toutes les villes
	 * 
	 * @Return Retourn une liste d'entité ville
	 */
	public List<City> findAll();
}

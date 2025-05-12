package com.WB.API.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.WB.API.model.Country;

/*
 * Repository permettant de manipuler un pays dans la base de données
 */
@Repository
public interface CountryRepository extends CrudRepository<Country, Integer> {

	/*
	 * Permet de récupérer la liste de tous les pays
	 * 
	 * @Return Retourn une liste d'entité pays
	 */
	public List<Country> findAll();
}

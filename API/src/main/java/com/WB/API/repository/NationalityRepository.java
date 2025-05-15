package com.WB.API.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.WB.API.model.Nationality;

/**
 * Repository permettant de manipuler une nationalité dans la base de données
 */
@Repository
public interface NationalityRepository extends CrudRepository<Nationality, Integer> {

	/**
	 * Récupére la liste de toutes les natioanlités
	 * 
	 * @Return Retourne une liste d'entité nationalité
	 */
	public List<Nationality> findAll();
}

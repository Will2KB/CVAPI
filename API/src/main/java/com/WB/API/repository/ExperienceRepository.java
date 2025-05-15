package com.WB.API.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.WB.API.model.Experience;

/**
 * Repository permettant de manipuler une expérience dans la base de données
 */
@Repository
public interface ExperienceRepository extends CrudRepository<Experience, Integer> {
	/**
	 * Récupére la liste de toutes les expériences
	 * 
	 * @Return Retourne une liste d'entité expérience
	 */
	public List<Experience> findAll();
}

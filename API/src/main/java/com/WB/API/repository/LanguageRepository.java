package com.WB.API.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.WB.API.model.Language;

/**
 * Repository permettant de manipuler une langue dans la base de données
 */
@Repository
public interface LanguageRepository extends CrudRepository<Language, Integer> {

	/**
	 * Récupére la liste de toutes les langues
	 * 
	 * @Return Retourne une liste d'entité langue
	 */
	public List<Language> findAll();
}

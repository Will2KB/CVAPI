package com.WB.API.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.WB.API.model.Skill;

/**
 * Repository permettant de manipuler une compétence dans la base de données
 */
@Repository
public interface SkillRepository extends CrudRepository<Skill, Integer> {

	/**
	 * Récupére la liste de toutes les compétences
	 * 
	 * @Return Retourne une liste d'entité compétence
	 */
	public List<Skill> findAll();
}

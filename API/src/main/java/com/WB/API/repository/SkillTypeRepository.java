package com.WB.API.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.WB.API.model.SkillType;

/**
 * Repository permettant de manipuler un type de compétence dans la base de
 * données
 */
@Repository
public interface SkillTypeRepository extends CrudRepository<SkillType, Integer> {

	/**
	 * Récupére la liste de tous les types de compétence
	 * 
	 * @Return Retourne une liste d'entité de type de compétence
	 */
	public List<SkillType> findAll();
}

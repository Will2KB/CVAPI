package com.WB.API.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.WB.API.model.SkillType;

/*
 * Repository permettant de manipuler un type de compétence dans la base de données
 */
@Repository
public interface SkillTypeRepository extends CrudRepository<SkillType, Integer> {

	/*
	 * Permet de récupérer la liste de tous les types de compétences
	 * 
	 * @Return Retourn une liste d'entité type de compétence
	 */
	public List<SkillType> findAll();
}

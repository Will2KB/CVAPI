package com.WB.API.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.WB.API.model.Person;

/**
 * Repository permettant de manipuler une personne dans la base de données
 */
@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {

	/**
	 * Récupére la première occurance trouvée correspondant au nom et au prénom
	 * 
	 * @param name:      nom à rechercher
	 * @param firstName: prénom à rechercher
	 * 
	 * @return Retourne une entité personne ou NULL si aucune occurance n'est
	 *         trouvée
	 */
	public Person findFirstPersonByNameAndFirstName(String name, String firstName);

	/**
	 * Récupére la première occurance trouvée correspondant à l'email recherché
	 * 
	 * @param email: email à rechercher
	 * 
	 * @return Retourne une entité personne ou NULL si aucune occurance n'est
	 *         trouvée
	 */
	public Person findFirstPersonByMail(String mail);

	/**
	 * Récupére la liste de toutes les personnes
	 * 
	 * @Return Retourne une liste d'entité personne
	 */
	public List<Person> findAll();
}

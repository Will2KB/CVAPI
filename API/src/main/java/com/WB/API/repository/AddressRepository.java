package com.WB.API.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.WB.API.model.Address;

/**
 * Repository permettant de manipuler une adresse dans la base de données
 */
@Repository
public interface AddressRepository extends CrudRepository<Address, Integer> {

	/**
	 * Récupére la liste de toutes les adresses
	 * 
	 * @Return Retourne une liste d'entité adresse
	 */
	public List<Address> findAll();

}

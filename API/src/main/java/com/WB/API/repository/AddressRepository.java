package com.WB.API.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.WB.API.model.Address;

/*
 * Repository permettant de manipuler une adresse dans la base de données
 */
@Repository
public interface AddressRepository extends CrudRepository<Address, Integer> {

}

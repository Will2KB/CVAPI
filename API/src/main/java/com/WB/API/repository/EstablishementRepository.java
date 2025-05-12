package com.WB.API.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.WB.API.model.Establishment;

/*
 * Repository permettant de manipuler un établissement dans la base de données
 */
@Repository
public interface EstablishementRepository extends CrudRepository<Establishment, Integer> {

}

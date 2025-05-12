package com.WB.API.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.WB.API.model.Hobby;

/*
 * Repository permettant de manipuler une passion dans la base de données
 */
@Repository
public interface HobbyRepository extends CrudRepository<Hobby, Integer> {

}

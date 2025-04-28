package com.WB.API.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.WB.API.model.Establishement;

@Repository
public interface EstablishementRepository extends CrudRepository<Establishement, Integer> {

}

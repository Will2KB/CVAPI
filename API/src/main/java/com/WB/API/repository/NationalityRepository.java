package com.WB.API.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.WB.API.model.Nationality;

@Repository
public interface NationalityRepository extends CrudRepository<Nationality, Integer> {
	public List<Nationality> findAll();
}

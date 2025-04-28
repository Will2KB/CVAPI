package com.WB.API.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.WB.API.model.City;

@Repository
public interface CityRepository extends CrudRepository<City, Integer> {

	public City findFirstCityByName(String name);

	public List<City> findAll();
}

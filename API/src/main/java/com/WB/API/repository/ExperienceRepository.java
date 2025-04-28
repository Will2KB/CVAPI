package com.WB.API.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.WB.API.model.Experience;

@Repository
public interface ExperienceRepository extends CrudRepository<Experience, Integer> {
	public List<Experience> findAll();
}

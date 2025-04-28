package com.WB.API.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.WB.API.model.SkillType;

@Repository
public interface SkillTypeRepository extends CrudRepository<SkillType, Integer> {
	public List<SkillType> findAll();
}

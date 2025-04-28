package com.WB.API.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.WB.API.model.Skill;

@Repository
public interface SkillRepository extends CrudRepository<Skill, Integer> {

	public List<Skill> findAll();
}

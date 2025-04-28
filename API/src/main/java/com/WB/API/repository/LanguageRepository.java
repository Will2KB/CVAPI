package com.WB.API.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.WB.API.model.Language;

@Repository
public interface LanguageRepository extends CrudRepository<Language, Integer> {
	public List<Language> findAll();
}

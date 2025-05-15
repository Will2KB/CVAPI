package com.WB.API.assertions;

import java.util.List;

/*
 * Objet pour créer les données pour les tests unitaires
 * Dans cette objet, on retrouve 2 classes possible: l'entité et le DTO
 */
public class TestDatas<T, U> {
	public final List<T> entities;
	public final List<U> dtos;

	public TestDatas(List<T> entities, List<U> dtos) {
		this.entities = entities;
		this.dtos = dtos;
	}
}
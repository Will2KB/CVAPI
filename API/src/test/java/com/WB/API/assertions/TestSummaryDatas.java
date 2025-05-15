package com.WB.API.assertions;

import java.util.List;

/*
 * Objet pour créer les données pour les tests unitaires
 * Dans cette objet, on retrouve 3 classes possible: l'entité, le DTO et le résumé du DTO
 */
public class TestSummaryDatas<T, U, V> {
	public final List<T> entities;
	public final List<U> dtos;
	public final List<V> dtoSummarries;

	public TestSummaryDatas(List<T> entities, List<U> dtos, List<V> dtoSummarries) {
		this.entities = entities;
		this.dtos = dtos;
		this.dtoSummarries = dtoSummarries;
	}
}
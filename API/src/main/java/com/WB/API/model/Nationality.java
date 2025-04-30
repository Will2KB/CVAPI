package com.WB.API.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "nationality")
public class Nationality {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@ManyToMany(mappedBy = "nationalities", cascade = CascadeType.ALL)
	private List<Person> persons;

	public Nationality() {
		this.persons = new ArrayList<>();
	}

	public Nationality(int id, String name) {
		this();
		this.id = id;
		this.name = name;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

}

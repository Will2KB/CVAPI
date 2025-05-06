package com.WB.API.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "city")
public class City {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "zip")
	private Integer zipCode;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "country_id")
	private Country country;

	public City() {

	}

	public City(int id) {
		this();
		this.id = id;
	}

	public City(int id, String name, Integer zipCode) {
		this(id);
		this.name = name;
		this.zipCode = zipCode;
	}

	public City(String name, Integer zipCode) {
		this();
		this.name = name;
		this.zipCode = zipCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getZipCode() {
		return zipCode;
	}

	public void setZip_code(Integer zip_code) {
		this.zipCode = zip_code;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public int getId() {
		return id;
	}

}

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
@Table(name = "address")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "nb_street")
	private Integer streetNumber;

	@Column(name = "street")
	private String street;

	@Column(name = "complement")
	private String complement;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "city_id")
	private City city;

	public Address() {
	}

	public Address(int id) {
		this.id = id;
	}

	public Address(int id, Integer streetNumber, String street, String complement) {
		this(id);
		this.streetNumber = streetNumber;
		this.street = street;
		this.complement = complement;
	}

	public int getId() {
		return id;
	}

	public Integer getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(Integer nb_street) {
		this.streetNumber = nb_street;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
}

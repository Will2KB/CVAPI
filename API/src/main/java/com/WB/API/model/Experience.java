package com.WB.API.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL) // Ignore les champs null dans le JSON
@Entity
@Table(name = "experience")
@Transactional
@DynamicUpdate
public class Experience {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@NotBlank(message = "Le nom de l'expérience ne peut pas être vide")
	@Column(name = "name")
	private String name;

	@NotNull(message = "La date de début d'expérience doit être renseigné")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "date_begining")
	private LocalDate dateBegining;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "date_ending")
	private LocalDate dateEnding;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "establishment_id")
	private Establishement establishement;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "city_id")
	private City city;

	@Column(name = "mission")
	private String mission;

	@Column(name = "description")
	private String description;

	@Column(name = "is_formation")
	private boolean isFormation;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "experience_developp_skill", joinColumns = @JoinColumn(name = "experience_id"), inverseJoinColumns = @JoinColumn(name = "skill_id"))
	private List<Skill> skills;

	public Experience() {
		this.isFormation = false;
		this.skills = new ArrayList<>();
	}

	public Experience(int id, String name, LocalDate begin, LocalDate end, boolean isFormation) {
		this();
		this.id = id;
		this.name = name;
		this.dateBegining = begin;
		this.dateEnding = end;
		this.isFormation = isFormation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDateBegining() {
		return dateBegining;
	}

	public void setDateBegining(LocalDate dateBegining) {
		this.dateBegining = dateBegining;
	}

	public LocalDate getDateEnding() {
		return dateEnding;
	}

	public void setDateEnding(LocalDate dateEnding) {
		this.dateEnding = dateEnding;
	}

	public Establishement getEstablishement() {
		return establishement;
	}

	public void setEstablishement(Establishement establishement) {
		this.establishement = establishement;
	}

	public String getMission() {
		return mission;
	}

	public void setMission(String mission) {
		this.mission = mission;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isFormation() {
		return isFormation;
	}

	public void setFormation(boolean isFormation) {
		this.isFormation = isFormation;
	}

	public int getId() {
		return id;
	}

	public City getCity() {
		if (city == null)
			if (establishement == null)
				return null;
			else if (establishement.getAddress() == null)
				return null;
			else
				return establishement.getAddress().getCity();
		else
			return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

}

package com.WB.API.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

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

@Entity
@Table(name = "experience")
@Transactional
@DynamicUpdate
public class Experience {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "date_begining")
	private LocalDate dateBegining;

	@Column(name = "date_ending")
	private LocalDate dateEnding;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "establishment_id")
	private Establishment establishement;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "city_id")
	private City city;

	@Column(name = "mission")
	private String mission;

	@JdbcTypeCode(SqlTypes.JSON)
	@Column(name = "descriptions", columnDefinition = "json")
	private List<String> descriptions;

	@Column(name = "is_formation")
	private boolean isFormation;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "experience_developp_skill", joinColumns = @JoinColumn(name = "experience_id"), inverseJoinColumns = @JoinColumn(name = "skill_id"))
	private List<Skill> skills;

	public Experience() {
		this.setFormation(false);
		this.skills = new ArrayList<>();
	}

	public Experience(int id, String name, LocalDate begin, LocalDate end, boolean isFormation, String mission) {
		this();
		this.id = id;
		this.setName(name);
		this.setDateBegining(begin);
		this.setDateEnding(end);
		this.setFormation(isFormation);
		this.setMission(mission);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDateBeginning() {
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

	public Establishment getEstablishement() {
		return establishement;
	}

	public void setEstablishement(Establishment establishement) {
		this.establishement = establishement;
	}

	public String getMission() {
		return mission;
	}

	public void setMission(String mission) {
		this.mission = mission;
	}

	public List<String> getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(List<String> descriptions) {
		this.descriptions = descriptions;
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

	/**
	 * Recupère la ville dans laquelle s'est déoulé l'expérience
	 * 
	 * @return Retourne l'objet ville si elle est renseigné, sinon la méthode
	 *         retourne NULL
	 */
	public City getCity() {
		// Si le paramètre City est null
		if (city == null) {
			// On vérifie l'adresse de l'établissement concerné
			if (establishement == null || establishement.getAddress() == null) {
				// Si aucun établissement ou aucune adresse alors on renvoie NULL
				return null;
			} else {
				// Sinon on renvoie la ville de l'établissement
				return establishement.getAddress().getCity();
			}
		} else {
			// Sinon on renvoie directement la ville renseignée
			return city;
		}
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

	public boolean hasDescription() {
		return this.descriptions != null && this.descriptions.size() > 0;
	}

	public boolean hasSkills() {
		return this.skills.size() > 0;
	}

}

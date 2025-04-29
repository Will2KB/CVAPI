package com.WB.API.model;

import java.time.LocalDate;
import java.time.Period;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@JsonInclude(JsonInclude.Include.NON_NULL) // Ignore les champs null dans le JSON
@Entity
@Table(name = "person")
@Transactional
@DynamicUpdate
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@NotBlank(message = "Le nom ne peut pas être vide")
	@Column(name = "name")
	private String name;

	@NotBlank(message = "Le prénom ne peut pas être vide")
	@Column(name = "first_name")
	private String firstName;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "birthday")
	private LocalDate birthday;

	@Email(message = "L'adresse mail n'est pas valide")
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "L'adresse mail doit contenir un point dans le nom de domaine")
	@NotBlank(message = "L'adresse mail ne peut pas être vide")
	@Column(name = "mail")
	private String mail;

	@Pattern(regexp = "^(\\+([1-9]{1,3})\\d{4,14}|0\\d{9})$", message = "Numéro de téléphone invalide : doit être un numéro international (+...) ou français (0...)")
	@Column(name = "phone")
	private String phone;

	@Column(name = "title")
	private String title;

	@Column(name = "subtitle")
	private String subtitle;

	@Column(name = "personal_values")
	private String personalValues;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "address_id")
	private Address address;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "person_id")
	private List<Experience> experiences;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "person_has_hobby", joinColumns = @JoinColumn(name = "person_id"), inverseJoinColumns = @JoinColumn(name = "hobby_id"))
	private List<Hobby> hobbies;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "person_has_nationality", joinColumns = @JoinColumn(name = "person_id"), inverseJoinColumns = @JoinColumn(name = "nationality_id"))
	private List<Nationality> nationalities;

	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<SpokenLanguage> spokenLanguages;

	public Person() {
		this.experiences = new ArrayList<>();
		this.hobbies = new ArrayList<>();
		this.nationalities = new ArrayList<>();
		this.spokenLanguages = new ArrayList<>();
	}

	public Person(int id) {
		this();
		this.id = id;
	}

	public Person(String name, String firstName, String eMail, String phone) {
		this();
		this.name = name;
		this.firstName = firstName;
		this.mail = eMail;
		this.phone = phone;
	}

	public Person(int id, String name, String firstName, String eMail, String phone) {
		this(name, firstName, eMail, phone);
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public int getAge() {
		if (birthday == null)
			return 0;

		return Period.between(birthday, LocalDate.now()).getYears();
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		if (phone != null) {
			this.phone = phone.replaceAll("\\s+", "");
		} else {
			this.phone = null;
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getPersonalValues() {
		return personalValues;
	}

	public void setPersonalValues(String personalValues) {
		this.personalValues = personalValues;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Experience> getExperiences() {
		return experiences;
	}

	public void setExperiences(List<Experience> experiences) {
		this.experiences = experiences;
	}

	public List<Hobby> getHobbies() {
		return hobbies;
	}

	public void setHobbies(List<Hobby> hobbies) {
		this.hobbies = hobbies;
	}

	public void addHobby(Hobby hobby) {
		hobbies.add(hobby);
	}

	public void removeHobby(Hobby hobby) {
		hobbies.remove(hobby);
	}

	public List<Nationality> getNationalities() {
		return nationalities;
	}

	public void setNationalities(List<Nationality> nationalities) {
		this.nationalities = nationalities;
	}

	public void addNationality(Nationality nationality) {
		nationalities.add(nationality);
		nationality.getPersons().add(this);
	}

	public void removeNationality(Nationality nationality) {
		nationalities.remove(nationality);
		nationality.getPersons().remove(this);
	}

	public int getId() {
		return id;
	}

	public List<SpokenLanguage> getSpokenLanguages() {
		return spokenLanguages;
	}

	public void setSpokenLanguages(List<SpokenLanguage> spokenLanguages) {
		this.spokenLanguages = spokenLanguages;
	}

	public void addSpokenLanguage(SpokenLanguage spokenLanguage) {
		spokenLanguages.add(spokenLanguage);
		spokenLanguage.setPerson(this);
	}

	public void removeSpokenLanguage(SpokenLanguage spokenLanguage) {
		spokenLanguages.remove(spokenLanguage);
		spokenLanguage.setPerson(null);
	}
}

package com.WB.API.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.DynamicUpdate;

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

@Entity
@Table(name = "person")
@Transactional
@DynamicUpdate
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "birthday")
	private LocalDate birthdate;

	@Column(name = "mail")
	private String mail;

	@Column(name = "phone")
	private String phone;

	@Column(name = "title")
	private String title;

	@Column(name = "subtitle")
	private String subtitle;

	@Column(name = "personal_values")
	private String personalValues;

	@Column(name = "linkedinlink")
	private String linkedInLink;

	@Column(name = "githublink")
	private String gitHubLink;

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
		this.setName(name);
		this.setFirstName(firstName);
		this.setMail(eMail);
		this.setPhone(phone);
	}

	public Person(String name, String firstName, String eMail, String phone, String title, String subTitle,
			LocalDate birthDate, String personalValues, String linkedInLink, String gitHubLink) {
		this(name, firstName, eMail, phone);
		this.setTitle(title);
		this.setSubtitle(subTitle);
		this.setBirthdate(birthDate);
		this.setPersonalValues(personalValues);
		this.setLinkedInLink(linkedInLink);
		this.setGitHubLink(gitHubLink);
	}

	public Person(int id, String name, String firstName, String eMail, String phone) {
		this(name, firstName, eMail, phone);
		this.id = id;
	}

	public Person(int id, String name, String firstName, String eMail, String phone, String title, String subTitle,
			LocalDate birthDate, String personalValues, String linkedInLink, String gitHubLink) {
		this(id, name, firstName, eMail, phone);
		this.setTitle(title);
		this.setSubtitle(subTitle);
		this.setBirthdate(birthDate);
		this.setPersonalValues(personalValues);
		this.setLinkedInLink(linkedInLink);
		this.setGitHubLink(gitHubLink);
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

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthday) {
		this.birthdate = birthday;
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

	public String getLinkedInLink() {
		return linkedInLink;
	}

	public void setLinkedInLink(String linkedInLink) {
		this.linkedInLink = linkedInLink;
	}

	public String getGitHubLink() {
		return gitHubLink;
	}

	public void setGitHubLink(String gitHubLink) {
		this.gitHubLink = gitHubLink;
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

	/**
	 * Créé une chaîne de caractères des compétences clés d'une personne séparé par
	 * une virgule
	 * 
	 * @param experiences
	 * @return la chaîne de caractères listant les compétences clés
	 */
	public List<String> getKeySkills() {

		List<String> keySkills = new ArrayList<>();

		for (Experience experience : this.experiences) {
			for (Skill skill : experience.getSkills()) {
				// Si la compétence est active et qu'elle est de type "key"
				if (skill.isEnable() && skill.getType().getId() == 3) {
					keySkills.add(skill.getName());
				}
			}
		}

		return keySkills;

	}
}

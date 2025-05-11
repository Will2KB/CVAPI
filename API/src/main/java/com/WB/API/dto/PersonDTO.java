package com.WB.API.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.Valid;

@JsonInclude(JsonInclude.Include.NON_NULL) // Ignore les champs null dans le JSON
public class PersonDTO {

	@Valid
	private PersonSummaryDTO summary;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthDate;
	private int age;
	private String subtitle;
	private String personalValues;

	private AddressDTO address;
	private List<NationalityDTO> nationalities;
	private List<HobbyDTO> hobbies;
	private List<SpokenLanguageDTO> spokenLanguages;
	private List<ExperienceSummaryDTO> experiences;

	public PersonDTO() {
		this.loadSummary();
		this.address = new AddressDTO();
		this.nationalities = new ArrayList<>();
		this.spokenLanguages = new ArrayList<>();
		this.experiences = new ArrayList<>();
	}

	public PersonDTO(String name, String firstName, String eMail, String phone) {
		this();
		this.summary.setName(name);
		this.summary.setFirstName(firstName);
		this.summary.setMail(eMail);
		this.summary.setPhone(phone);
	}

	public PersonDTO(int id, String name, String firstName, String eMail, String phone, String title) {
		this(name, firstName, eMail, phone);
		this.summary.setTitle(title);
		this.summary.setId(id);
	}

	public PersonDTO(int id, String name, String firstName, String eMail, String phone, String title, String subTitle,
			LocalDate birthDate, String personValues) {
		this(id, name, firstName, eMail, phone, title);
		this.setSubtitle(subTitle);
		this.setBirthDate(birthDate);
		this.setPersonalValues(personValues);
	}

	private void loadSummary() {
		if (this.summary == null)
			this.summary = new PersonSummaryDTO();
	}

	public PersonSummaryDTO getSummary() {
		this.loadSummary();
		return summary;
	}

	public void setSummary(PersonSummaryDTO summary) {
		this.summary = summary;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public AddressDTO getAddress() {
		return address;
	}

	public void setAddress(AddressDTO address) {
		this.address = address;
	}

	public int getId() {
		return this.getSummary().getId();
	}

	public void setId(int id) {
		this.getSummary().setId(id);
	}

	public String getName() {
		return this.getSummary().getName();
	}

	public void setName(String name) {
		this.getSummary().setName(name);
	}

	public String getFirstName() {
		return this.getSummary().getFirstName();
	}

	public void setFirstName(String firstName) {
		this.getSummary().setFirstName(firstName);
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getMail() {
		return this.getSummary().getMail();
	}

	public void setMail(String mail) {
		this.getSummary().setMail(mail);
	}

	public String getPhone() {
		return this.getSummary().getPhone();
	}

	public void setPhone(String phone) {
		this.getSummary().setPhone(phone);
	}

	public String getTitle() {
		return this.getSummary().getTitle();
	}

	public void setTitle(String title) {
		this.getSummary().setTitle(title);
	}

	public String getSubtitle() {
		return this.subtitle;
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

	public List<HobbyDTO> getHobbies() {
		return hobbies;
	}

	public void setHobbies(List<HobbyDTO> hobbies) {
		this.hobbies = hobbies;
	}

	public List<NationalityDTO> getNationalities() {
		return nationalities;
	}

	public void setNationalities(List<NationalityDTO> nationalities) {
		this.nationalities = nationalities;
	}

	public List<SpokenLanguageDTO> getSpokenLanguages() {
		return spokenLanguages;
	}

	public void setSpokenLanguages(List<SpokenLanguageDTO> spokenLanguages) {
		this.spokenLanguages = spokenLanguages;
	}

	public List<ExperienceSummaryDTO> getExperiences() {
		return experiences;
	}

	public void setExperiences(List<ExperienceSummaryDTO> experiences) {
		this.experiences = experiences;
	}
}

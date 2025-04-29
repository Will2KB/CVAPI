package com.WB.API.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersonDTO {

	private PersonSummaryDTO summary;

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
		this.summary = new PersonSummaryDTO();
		this.address = new AddressDTO();
		this.nationalities = new ArrayList<>();
		this.spokenLanguages = new ArrayList<>();
		this.experiences = new ArrayList<>();
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

	public PersonSummaryDTO getSummary() {
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

	public int getId() {
		return summary.getId();
	}

	public void setId(int id) {
		this.summary.setId(id);
	}

	public String getName() {
		return this.summary.getName();
	}

	public void setName(String name) {
		this.summary.setName(name);
	}

	public String getFirstName() {
		return this.summary.getFirstName();
	}

	public void setFirstName(String firstName) {
		this.summary.setFirstName(firstName);
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getMail() {
		return this.summary.getMail();
	}

	public void setMail(String mail) {
		this.summary.setMail(mail);
	}

	public String getPhone() {
		return this.summary.getPhone();
	}

	public void setPhone(String phone) {
		this.summary.setPhone(phone);
	}

	public String getTitle() {
		return this.summary.getTitle();
	}

	public void setTitle(String title) {
		this.summary.setTitle(title);
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

}

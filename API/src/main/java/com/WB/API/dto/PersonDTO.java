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
	private List<String> keySkills;
	private List<NationalityDTO> nationalities;
	private List<HobbyDTO> hobbies;
	private List<SpokenLanguageDTO> spokenLanguages;
	private List<ExperienceSummaryDTO> experiences;

	public PersonDTO() {
		this.loadSummary();
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

	public PersonDTO(int id, String name, String firstName, String eMail, String phone, String title,
			String linkedInLink, String gitHubLink) {
		this(name, firstName, eMail, phone);
		this.summary.setTitle(title);
		this.summary.setId(id);
		this.summary.setLinkedInLink(linkedInLink);
		this.summary.setGitHubLink(gitHubLink);
	}

	public PersonDTO(int id, String name, String firstName, String eMail, String phone, String title, String subTitle,
			LocalDate birthDate, String personValues, List<String> keySkills, String linkedInLink, String gitHubLink) {
		this(id, name, firstName, eMail, phone, title, linkedInLink, gitHubLink);
		this.setSubtitle(subTitle);
		this.setBirthDate(birthDate);
		this.setPersonalValues(personValues);
		this.setKeySkills(keySkills);
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

	public AddressDTO getAddress() {
		return this.summary.getAddress();
	}

	public void setAddress(AddressDTO adress) {
		this.summary.setAddress(adress);
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
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

	public String getLinkedInLink() {
		return this.getSummary().getLinkedInLink();
	}

	public void setLinkedInLink(String link) {
		this.getSummary().setLinkedInLink(link);
	}

	public String getGitHubLink() {
		return this.getSummary().getGitHubLink();
	}

	public void setGitHubLink(String link) {
		this.getSummary().setGitHubLink(link);
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

	public List<String> getKeySkills() {
		return keySkills;
	}

	public void setKeySkills(List<String> keySkills) {
		this.keySkills = keySkills;
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

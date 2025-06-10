package com.WB.API.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@JsonInclude(JsonInclude.Include.NON_NULL) // Ignore les champs null dans le JSON
public class PersonSummaryDTO {

	private int id;

	@NotBlank(message = "Le nom ne peut pas être vide")
	private String name;

	@NotBlank(message = "Le prénom ne peut pas être vide")
	private String firstName;

	@Email(message = "L'adresse mail n'est pas valide")
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "L'adresse mail doit contenir un point dans le nom de domaine")
	@NotBlank(message = "L'adresse mail ne peut pas être vide")
	private String mail;

	@Pattern(regexp = "^(\\+([1-9]{1,3})\\d{4,14}|0\\d{9})$", message = "Numéro de téléphone invalide : doit être un numéro international (+...) ou français (0...)")
	private String phone;

	private AddressDTO address;

	private String title;

	private String linkedInLink;

	private String gitHubLink;

	public PersonSummaryDTO() {
		this.address = new AddressDTO();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
		this.phone = phone;
	}

	public AddressDTO getAddress() {
		return address;
	}

	public void setAddress(AddressDTO address) {
		this.address = address;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

}

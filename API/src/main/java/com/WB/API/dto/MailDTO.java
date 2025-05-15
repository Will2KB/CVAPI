package com.WB.API.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class MailDTO {

	@Email(message = "L'adresse mail n'est pas valide")
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "L'adresse mail doit contenir un point dans le nom de domaine")
	@NotBlank(message = "L'adresse mail ne peut pas être vide")
	private String mailFrom;
	@NotBlank(message = "Le sujet ne peut pas être vide")
	private String subject;
	@NotBlank(message = "Le corps du mail ne peut pas être vide")
	private String body;

	public MailDTO() {

	}

	public MailDTO(String mailFrom, String subject, String body) {
		this();
		this.setMailFrom(mailFrom);
		this.setSubject(subject);
		this.setBody(body);
	}

	public String getMailFrom() {
		return mailFrom;
	}

	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String title) {
		this.subject = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}

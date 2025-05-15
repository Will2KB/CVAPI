package com.WB.API.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.WB.API.dto.MailDTO;

/**
 * Service pour envoyer les mails permettant aux recruteurs de prendre contact
 */
@Service
public class MailService {

	@Autowired
	private JavaMailSender mailSender;

	/**
	 * Envoie le mail demandé par l'utilisateur ainsi qu'une confirmation à
	 * l'expéditeur
	 * 
	 * @param mailDTO : email à envoyer
	 * @throws MailException : Exception levé lors de l'envoie des emails
	 */
	public void sendSimpleMail(MailDTO mailDTO) throws MailException {

		SimpleMailMessage messageTo = this.getMailMessageTo(mailDTO);
		mailSender.send(messageTo);
		mailSender.send(this.getConfirmMailMessage(messageTo));
	}

	/**
	 * Composition du mail à envoyer
	 * 
	 * @param mailDTO : Données fournies par l'utilisateur pour l'envoie du mail
	 * @return Retourne le mail à envoyer
	 */
	public SimpleMailMessage getMailMessageTo(MailDTO mailDTO) {
		SimpleMailMessage messageTo = new SimpleMailMessage();
		// Envoyé par l'utilisateur à qui il faudra répondre pour plus d'information
		messageTo.setReplyTo(mailDTO.getMailFrom());
		// Envoyé à William Baroni
		messageTo.setTo("baroni.will@gmail.com");
		messageTo.setSubject(mailDTO.getSubject());
		messageTo.setText(mailDTO.getBody());

		return messageTo;
	}

	/**
	 * Composition du mail de confirmation
	 * 
	 * @param messageTo : Le mail déjà envoyé que l'on souhaite confirmer à
	 *                  l'expéditeur
	 * @return Le mail de confirmation à envoyer
	 */
	public SimpleMailMessage getConfirmMailMessage(SimpleMailMessage messageTo) {
		SimpleMailMessage messageFrom = new SimpleMailMessage();
		// On envoie le mail de confirmaiton à l'expéditeur
		messageFrom.setTo(messageTo.getReplyTo());
		// On signale bien qu'il peut répondre à William Baroni
		messageFrom.setReplyTo("baroni.will@gmail.com");
		// On précise l'objet du mail
		messageFrom.setSubject("Confirmation d'envoi : " + messageTo.getSubject());
		// On construit le corps du mail pour signaler que le premier mail a été envoyé
		String bodyFrom = "✅ Vous avez envoyé le mail suivant avec succès à baroni.will@gmail.com :\n\n"
				+ messageTo.getText();
		messageFrom.setText(bodyFrom);

		return messageFrom;
	}
}

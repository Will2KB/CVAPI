package com.WB.API.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ActiveProfiles;

import com.WB.API.dto.MailDTO;

/*
 * Tests du service des mails
 * Lors de ces test, nous souhaitons vérifier le fonctionnement du service 
 * c'est pourquoi le comportement du JavaMailSender (auquel le service fait appel) sera mocké
 */
@ActiveProfiles("test")
@DisplayName("Test du service des mails")
@ExtendWith(MockitoExtension.class)
class MailServiceTests {

	@Mock
	private JavaMailSender mailSender;

	@InjectMocks
	private MailService mailService;

	@Test
	@DisplayName("Envoie d'un mail")
	void testSendMail_ShouldSendTwoMailsWithCorrectContent() {
		// Arrange
		MailDTO mailDTO = new MailDTO("expediteur@example.com", "Sujet", "Corps du message");

		ArgumentCaptor<SimpleMailMessage> messageCaptor = ArgumentCaptor.forClass(SimpleMailMessage.class);

		// Act
		mailService.sendSimpleMail(mailDTO);

		// Assert
		verify(mailSender, times(2)).send(messageCaptor.capture());
		List<SimpleMailMessage> messages = messageCaptor.getAllValues();

		// Premier mail (au destinataire)
		SimpleMailMessage toMessage = messages.get(0);
		Assertions.assertEquals("baroni.will@gmail.com", toMessage.getTo()[0]);
		Assertions.assertEquals("Sujet", toMessage.getSubject());
		Assertions.assertEquals("Corps du message", toMessage.getText());
		Assertions.assertEquals("expediteur@example.com", toMessage.getReplyTo());

		// Deuxième mail (confirmation à l'expéditeur)
		SimpleMailMessage fromMessage = messages.get(1);
		Assertions.assertEquals("expediteur@example.com", fromMessage.getTo()[0]);
		Assertions.assertEquals("Confirmation d'envoi : Sujet", fromMessage.getSubject());
		Assertions.assertTrue(fromMessage.getText().contains("Vous avez envoyé le mail suivant"));
	}

	@Test
	void sendSimpleMail_shouldThrowMailException_ifSendingFails() {
		// Arrange
		MailDTO mailDTO = new MailDTO("expediteur@example.com", "Sujet", "Corps du message");

		doThrow(new MailSendException("Erreur SMTP")).when(mailSender).send(any(SimpleMailMessage.class));

		// Act + Assert
		Assertions.assertThrows(MailSendException.class, () -> mailService.sendSimpleMail(mailDTO));
	}
}
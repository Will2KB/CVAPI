package com.WB.API.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.WB.API.dto.MailDTO;
import com.WB.API.dto.PersonSummaryDTO;
import com.WB.API.service.MailService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ActiveProfiles("test")
@DisplayName("Test du controleur d'envoie de mails")
@WebMvcTest(controllers = MailController.class)
class MailControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private MailService mailService;

	@Test
	@DisplayName("POST /mail - envoie un mail et retourne 200 OK")
	void testSendMail_ReturnsOk() throws Exception {
		// Arrange
		PersonSummaryDTO mailFrom = new PersonSummaryDTO();
		mailFrom.setMail("expediteur@example.com");

		PersonSummaryDTO mailTo = new PersonSummaryDTO();
		mailTo.setMail("destinataire@example.com");

		MailDTO mailDTO = new MailDTO("baroni.will@gmail.com", "Sujet", "Corps du message");

		// Act + Assert
		mockMvc.perform(post("/mail").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(mailDTO))).andExpect(status().isOk());
		verify(mailService).sendSimpleMail(any(MailDTO.class));
	}
}
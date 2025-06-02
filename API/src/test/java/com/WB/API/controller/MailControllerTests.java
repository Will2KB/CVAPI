package com.WB.API.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.WB.API.config.RateLimitingFilter;
import com.WB.API.dto.MailDTO;
import com.WB.API.service.MailService;
import com.WB.API.service.RecaptchaService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ActiveProfiles("test")
@DisplayName("Test du controleur d'envoie de mails")
@WebMvcTest(controllers = MailController.class)
class MailControllerTests {

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private MailService mailService;

	@MockBean
	private RecaptchaService recaptchaService;

	private final ObjectMapper objectMapper = new ObjectMapper();

	@BeforeEach
	void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).addFilter(new RateLimitingFilter()).build();

		when(recaptchaService.isCaptchaValid(any())).thenReturn(true);
	}

	private String asJson(Object obj) throws Exception {
		return objectMapper.writeValueAsString(obj);
	}

	private MailDTO createMail() {
		MailDTO mail = new MailDTO("expediteur@example.com", "Sujet test", "Corps du message", "fake-recaptcha-token");

		return mail;
	}

	@Test
	@DisplayName("POST /mail - envoie un mail et retourne 200 OK")
	void testSendMail_ReturnsOk() throws Exception {
		// Arrange + Act
		mockMvc.perform(post("/mail").contentType(MediaType.APPLICATION_JSON).content(asJson(createMail())))
				.andExpect(status().isOk());

		// Assert
		verify(mailService).sendSimpleMail(any(MailDTO.class));
	}

	@Test
	@DisplayName("POST /mail - dépassement de la limite de requêtes -> 429")
	void testRateLimitingExceeded_ReturnsTooManyRequests() throws Exception {
		// Arrange
		for (int i = 0; i < 10; i++) {
			mockMvc.perform(post("/mail").contentType(MediaType.APPLICATION_JSON).content(asJson(createMail())))
					.andExpect(status().isOk());
		}

		// Act + Assert
		mockMvc.perform(post("/mail").contentType(MediaType.APPLICATION_JSON).content(asJson(createMail())))
				.andExpect(status().isTooManyRequests());
	}
}
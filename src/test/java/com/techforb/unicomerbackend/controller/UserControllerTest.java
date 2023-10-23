package com.techforb.unicomerbackend.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.io.IOException;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.web.servlet.MockMvc;

import com.techforb.unicomerbackend.dto.CardRegisterDTO;
import com.techforb.unicomerbackend.dto.UserLoginRequestDTO;
import com.techforb.unicomerbackend.dto.UserRegisterRequestDTO;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private JacksonTester<UserRegisterRequestDTO> userJsonPostTester;

	@Autowired
	private JacksonTester<UserLoginRequestDTO> userLoginJsonPostTester;

	@Autowired
	private JacksonTester<CardRegisterDTO> cardRegisterJsonPostTester;

	@BeforeEach
	public void postUser() throws IOException, Exception {
		var response = mockMvc.perform(post("/users/register").contentType(MediaType.APPLICATION_JSON)
				.content(userJsonPostTester
						.write(new UserRegisterRequestDTO("Usuário de teste", "teste12345", "senha1234", "senha1234"))
						.getJson()))
				.andReturn().getResponse();

	}

	@Test
	public void shouldReturnAllUsers() throws Exception {

		var response = mockMvc.perform(get("/users")).andReturn().getResponse();

		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
	}

	@Test
	public void shouldReturnOneUser() throws Exception {

		var response = mockMvc.perform(get("/users/1/details")).andReturn().getResponse();

		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
	}

	@Test
	public void shouldNotReturnOneUser() throws Exception {
		var response = mockMvc.perform(get("/users/0/details")).andReturn().getResponse();

		assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
	}

	@Test
	public void shouldNotPostOneUser() throws Exception {

		var response = mockMvc.perform(post("/users/register").contentType(MediaType.APPLICATION_JSON)
				.content(userJsonPostTester
						.write(new UserRegisterRequestDTO("Usuário de teste", "teste1234", "senha152345", "senha1234"))
						.getJson()))
				.andReturn().getResponse();

		assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());

	}

	@Test
	public void shouldLoginUserSucessFull() throws Exception {

		var response = mockMvc
				.perform(post("/users/login").contentType(MediaType.APPLICATION_JSON).content(
						userLoginJsonPostTester.write(new UserLoginRequestDTO("teste12345", "senha1234")).getJson()))
				.andReturn().getResponse();

		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

	}

	@Test
	public void shouldNotLoginUserSucessFull() throws Exception {

		var response = mockMvc
				.perform(post("/users/login").contentType(MediaType.APPLICATION_JSON).content(
						userLoginJsonPostTester.write(new UserLoginRequestDTO("teste12345", "senha12345")).getJson()))
				.andReturn().getResponse();

		assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
	}

	@Test
	public void shouldReturnAllUserTransfers() throws Exception {

		var response = mockMvc.perform(get("/users/1/transfers")).andReturn().getResponse();

		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
	}

	@Test
	public void shouldReturnAllUserCards() throws Exception {

		var response = mockMvc.perform(get("/users/1/cards")).andReturn().getResponse();

		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
	}

	@Test
	public void shouldInsertUserCard() throws Exception {

		var response = mockMvc.perform(post("/users/1/insert-card").contentType(MediaType.APPLICATION_JSON)
				.content(cardRegisterJsonPostTester
						.write(new CardRegisterDTO("5323910334697652", LocalDateTime.now(), 331, "Teste")).getJson()))
				.andReturn().getResponse();

	}

	@Test
	public void shouldNotInsertUserCard() throws Exception {

		var response = mockMvc.perform(post("/users/1/insert-card").contentType(MediaType.APPLICATION_JSON)
				.content(cardRegisterJsonPostTester
						.write(new CardRegisterDTO("53239103346976", LocalDateTime.now(), 331, "Teste")).getJson()))
				.andReturn().getResponse();

		assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
	}

}

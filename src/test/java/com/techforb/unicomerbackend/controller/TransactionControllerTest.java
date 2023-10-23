package com.techforb.unicomerbackend.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

import com.techforb.unicomerbackend.dto.TransferRequestDTO;
import com.techforb.unicomerbackend.dto.UserRegisterRequestDTO;
import com.techforb.unicomerbackend.dto.UserTransactionRequestDTO;
import com.techforb.unicomerbackend.exception.TransferException;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class TransactionControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private JacksonTester<TransferRequestDTO> transferJsonTester;

	@Autowired
	private JacksonTester<UserTransactionRequestDTO> userTransactionJsonTester;

	@Autowired
	private JacksonTester<UserRegisterRequestDTO> userJsonPostTester;

	@BeforeEach
	public void postUserAndDeposit() throws IOException, Exception {
		var response = mockMvc.perform(post("/users/register").contentType(MediaType.APPLICATION_JSON)
				.content(userJsonPostTester
						.write(new UserRegisterRequestDTO("Usuário de teste 1", "teste12345", "senha1234", "senha1234"))
						.getJson()))
				.andReturn().getResponse();

		response = mockMvc.perform(post("/users/register").contentType(MediaType.APPLICATION_JSON)
				.content(userJsonPostTester.write(
						new UserRegisterRequestDTO("Usuário de teste 2", "teste123456", "senha1234", "senha1234"))
						.getJson()))
				.andReturn().getResponse();
		response = mockMvc
				.perform(post("/transfers/make-deposit").contentType(MediaType.APPLICATION_JSON)
						.content(userTransactionJsonTester
								.write(new UserTransactionRequestDTO(1L, new BigDecimal("1000"))).getJson()))
				.andReturn().getResponse();

	}

	@Test
	public void shouldNotMakeTransfer() throws IOException, Exception {

		assertThrows(NestedServletException.class, () -> {
			var response = mockMvc
					.perform(post("/transfers/make-transfer").contentType(MediaType.APPLICATION_JSON).content(
							transferJsonTester.write(new TransferRequestDTO(1L, 2L, new BigDecimal(100000))).getJson()))
					.andReturn().getResponse();

			assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
		});

	}

	@Test
	public void shouldMakeDeposit() throws IOException, Exception {
		var response = mockMvc
				.perform(post("/transfers/make-deposit").contentType(MediaType.APPLICATION_JSON)
						.content(userTransactionJsonTester
								.write(new UserTransactionRequestDTO(1L, new BigDecimal("1000"))).getJson()))
				.andReturn().getResponse();
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
	}

	@Test
	public void shouldMakeTransfer() throws IOException, Exception {
		var response = mockMvc
				.perform(post("/transfers/make-transfer").contentType(MediaType.APPLICATION_JSON).content(
						transferJsonTester.write(new TransferRequestDTO(1L, 2L, new BigDecimal(100))).getJson()))
				.andReturn().getResponse();
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
	}

	@Test
	public void shouldMakeWithdraw() throws IOException, Exception {
		var response = mockMvc
				.perform(post("/transfers/make-withdraw").contentType(MediaType.APPLICATION_JSON)
						.content(userTransactionJsonTester
								.write(new UserTransactionRequestDTO(1L, new BigDecimal("100"))).getJson()))
				.andReturn().getResponse();
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
	}

	@Test
	public void shouldNotMakeWithdraw() throws IOException, Exception {

		assertThrows(NestedServletException.class, () -> {
			var response = mockMvc
					.perform(post("/transfers/make-withdraw").contentType(MediaType.APPLICATION_JSON)
							.content(userTransactionJsonTester
									.write(new UserTransactionRequestDTO(1L, new BigDecimal("10000"))).getJson()))
					.andReturn().getResponse();

			assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
		});
	}

}

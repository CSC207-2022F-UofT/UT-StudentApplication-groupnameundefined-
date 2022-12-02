package backend;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import backend.controller.UserController;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import backend.form.UserForm.*;


public class UserControllerIntegrationTest extends ControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserController userController;

	@Test
	public void contextLoads() throws Exception {
		assertThat(userController).isNotNull();
	}

	@Test
	public void registerUserAPI_expectSuccess() throws Exception {
		// Valid input
		RegisterForm input = new RegisterForm(
				"Test Name",
				"test.name@email.com",
				"1234567Ab",
				"0123456789"
		);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/user/register")
						.contentType(MediaType.APPLICATION_JSON)
						.content(toJsonString(input)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.email", is("test.name@email.com")))
				.andExpect(jsonPath("$.studentProfile", nullValue()))
				.andExpect(jsonPath("$.sentFriendRequests", hasSize(0)));
	}

	@Test
	public void registerUserAPI_expectInvalidInput() throws Exception {
		// Input with invalid email, password not meeting requirement, phone too short
		RegisterForm input = new RegisterForm(
				"Test Name",
				"test.nameemail.com",
				"1234567A",
				"1234567"
		);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/user/register")
						.contentType(MediaType.APPLICATION_JSON)
						.content(toJsonString(input)))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errors", hasSize(3)))
				.andExpect(jsonPath("$.errors[*].field", containsInAnyOrder("email", "password", "phone")));
	}

}

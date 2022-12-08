package backend;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import backend.form.UserForm.*;
import backend.model.User;
import backend.service.UserService;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class UserControllerIntegrationTest extends ControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserService userService;

	@Test
	@Order(1)
	public void registerUser_expectSuccess() throws Exception {
		RegisterForm input = new RegisterForm(
				"Test Name",
				"test.name@email.com",
				"1234567Abc",
				"0123456789"
		);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/user/register")
						.contentType(MediaType.APPLICATION_JSON)
						.content(toJsonString(input)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.email", is("test.name@email.com")))
				.andExpect(jsonPath("$.studentProfile", nullValue()));
	}

	@Test
	@Order(2)
	public void registerUser_expectInvalidInput() throws Exception {
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

	@Test
	@Order(3)
	public void registerUser_expectEntityExist() throws Exception {
		RegisterForm input = new RegisterForm(
				"Test Name",
				"test.name@email.com",
				"1234567Abc",
				"0123456789"
		);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/user/register")
						.contentType(MediaType.APPLICATION_JSON)
						.content(toJsonString(input)))
				.andExpect(status().isConflict())
				.andExpect(jsonPath("$.code", is("EXIST-USER")));
	}

	@Test
	@Order(4)
	public void getAllUsers_expectSuccess() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/user/"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[*]", hasSize(1)));
	}

	@Test
	@Order(5)
	public void getUserById_expectSuccess() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/user/{id}", 1))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1)));
	}

	@Test
	@Order(6)
	public void getUserById_expectEntityNotFound() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/user/{id}", 2))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.code", is("NOTFOUND-USER")));
	}

	@Test
	@Order(7)
	public void loginUser_expectSuccess() throws Exception {
		LoginForm input = new LoginForm(
				"test.name@email.com",
				"1234567Abc"
		);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/user/login")
						.contentType(MediaType.APPLICATION_JSON)
						.content(toJsonString(input)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.loginStatus", is(true)))
				.andExpect(jsonPath("$.id", is(1)));
	}

	@Test
	@Order(8)
	public void loginUser_expectInvalidInput() throws Exception {
		// Invalid email and password, but should only check email validity
		LoginForm input = new LoginForm(
				"test.nameemail.com",
				"1234567abc"
		);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/user/login")
						.contentType(MediaType.APPLICATION_JSON)
						.content(toJsonString(input)))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errors", hasSize(1)))
				.andExpect(jsonPath("$.errors[*].field", contains("email")));
	}

	@Test
	@Order(9)
	public void loginUser_expectUnauthorized() throws Exception {
		// Incorrect password (extra 'd')
		LoginForm input = new LoginForm(
				"test.name@email.com",
				"1234567Abcd"
		);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/user/login")
						.contentType(MediaType.APPLICATION_JSON)
						.content(toJsonString(input)))
				.andExpect(status().isUnauthorized());
	}

	@Test
	@Order(10)
	public void logoutUser_expectSuccess() throws Exception {
		User user = userService.getUserById(1L);
		assertThat(user.getLoginStatus(), is(true));

		mockMvc.perform(MockMvcRequestBuilders.get("/api/user/logout/{id}", 1))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", is(1)));

		User _user = userService.getUserById(1L);
		assertThat(_user.getLoginStatus(), is(false));
	}

	@Test
	@Order(11)
	public void logoutUser_expectEntityNotFound() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/user/logout/{id}", 2))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.code", is("NOTFOUND-USER")));
	}

}

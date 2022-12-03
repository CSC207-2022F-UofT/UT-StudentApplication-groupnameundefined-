package backend;

import backend.form.StudentProfileForm.*;
import backend.form.UserForm;
import backend.model.User;
import backend.service.StudentProfileService;
import backend.service.UserService;

import static org.hamcrest.Matchers.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@Order(2)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentProfileControllerIntegrationTest extends ControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private StudentProfileService studentProfileService;

	@Autowired
	private UserService userService;

	@Test
	@Order(1)
	public void createStudentProfile_expectSuccess() throws Exception {
		UserForm.RegisterForm registerForm = new UserForm.RegisterForm(
				"Test Name",
				"test.name@email.com",
				"1234567Abc",
				"0123456789"
		);

		User user = userService.registerUser(registerForm);
		Long userId = user.getId();

		CreateStudentProfileForm input = new CreateStudentProfileForm(
				userId,
				"Computer Science",
				"Woodsworth",
				2020
		);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/student-profile/create")
						.contentType(MediaType.APPLICATION_JSON)
						.content(toJsonString(input)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.userId", is(1)))
				.andExpect(jsonPath("$.enrolmentYear", is(2020)))
				.andExpect(jsonPath("$.timetable", nullValue()))
				.andExpect(jsonPath("$.socialMediaProfile", nullValue()));
	}

	@Test
	@Order(2)
	public void createStudentProfile_expectEntityDoesNotExist_User() throws Exception {
		CreateStudentProfileForm input = new CreateStudentProfileForm(
				2L,
				"Computer Science",
				"Woodsworth",
				2020
		);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/student-profile/create")
						.contentType(MediaType.APPLICATION_JSON)
						.content(toJsonString(input)))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.code", is("NOTFOUND-USER")));
	}

	@Test
	@Order(3)
	public void createStudentProfile_expectInvalidInput() throws Exception {
		// College name too long, enrolment year exceeds limit.
		CreateStudentProfileForm input = new CreateStudentProfileForm(
				1L,
				"c".repeat(257),
				"Woodsworth",
				10000
		);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/student-profile/create")
						.contentType(MediaType.APPLICATION_JSON)
						.content(toJsonString(input)))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errors", hasSize(2)))
				.andExpect(jsonPath("$.errors[*].field", containsInAnyOrder("program", "enrolmentYear")));
	}
}

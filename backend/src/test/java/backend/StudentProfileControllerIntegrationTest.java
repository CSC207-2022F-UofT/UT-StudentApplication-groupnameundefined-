package backend;

import backend.form.HabitForm.*;
import backend.form.StudentProfileForm.*;
import backend.form.UserForm.*;
import backend.model.StudentProfile;
import backend.model.User;
import backend.service.HabitService;
import backend.service.StudentProfileService;
import backend.service.UserService;

import static java.util.Map.entry;
import static org.hamcrest.Matchers.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.*;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Order(2)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentProfileControllerIntegrationTest extends ControllerIntegrationTest {

	@Autowired
	Logger logger;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private StudentProfileService studentProfileService;

	@Autowired
	private HabitService habitService;

	@Autowired
	private UserService userService;

	@Test
	@Order(1)
	public void createStudentProfile_expectSuccess() throws Exception {
		RegisterForm registerForm = new RegisterForm(
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
				.andExpect(jsonPath("$.id", is(1)))
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

	@Test
	@Order(4)
	public void matchStudentProfileByHabit_expectSuccess() throws Exception {
		List<Map<String, Object>> testData = new ArrayList<>(5);
		for (Integer i = 1; i < 6; i++) {
			String stringI = i.toString();
			RegisterForm registerForm = new RegisterForm(
					"Test Name " + stringI,
					"test.name" + stringI + "@email.com",
					"1234567Abc",
					"012345678" + stringI
			);
			User user = userService.registerUser(registerForm);

			CreateStudentProfileForm cspForm = new CreateStudentProfileForm(
					user.getId(),
					"CS",
					"Woodsworth",
					2020
			);
			StudentProfile studentProfile = studentProfileService.createStudentProfile(cspForm);

			CreateHabitForm createHabitForm = new CreateHabitForm(
					studentProfile.getId(),
					i,
					i
			);
			habitService.createHabit(createHabitForm);
		}

		mockMvc.perform(MockMvcRequestBuilders.get("/api/student-profile/match-courses/{id}", 2))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[*].id", anyOf(contains(1, 3, 4, 5), contains(3, 1, 4, 5))));

		mockMvc.perform(MockMvcRequestBuilders.get("/api/student-profile/match-courses/{id}", 4))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[*].id", anyOf(contains(5, 3, 2, 1), contains(3, 5, 2, 1))));
	}
}

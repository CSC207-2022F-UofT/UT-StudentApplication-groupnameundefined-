package backend;

import backend.form.HabitForm.*;
import backend.form.StudentProfileForm.*;
import backend.form.UserForm.*;
import backend.model.StudentProfile;
import backend.model.User;
import backend.repository.UserRepository;
import backend.service.HabitService;
import backend.service.StudentProfileService;
import backend.service.UserService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.*;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

	@Autowired
	private UserRepository userRepository;

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

	@DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
	@Test
	@Order(4)
	public void matchStudentProfileByHabit_expectSuccess() throws Exception {
		userRepository.deleteAll();
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

		// MockMvc always return list in ascending order by id so we will directly call service method.
		List<StudentProfile> studentProfiles = studentProfileService.matchStudentProfileByHabit(4L);
		List<Long> studentProfileIds = studentProfiles.stream().map(StudentProfile::getId).toList();

		assertThat(studentProfileIds, anyOf(is(Arrays.asList(3L, 5L, 2L, 1L)), is(Arrays.asList(5L, 3L, 2L, 1L))));
	}
}

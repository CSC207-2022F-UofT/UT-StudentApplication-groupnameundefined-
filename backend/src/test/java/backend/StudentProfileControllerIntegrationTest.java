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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.*;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Order(2)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentProfileControllerIntegrationTest extends ControllerIntegrationTest {

	@Autowired
	Logger logger;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ResourceLoader resourceLoader;

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

	@Nested
	@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	public class MatchingTests {

		/**
		 * Sets up users and student profiles before testing matches
		 */
		@BeforeEach
		public void matchingTests_setup() {
			userRepository.deleteAll();
			for (int i = 1; i < 6; i++) {
				RegisterForm registerForm = new RegisterForm(
						"Test Name " + i,
						"test.name" + i + "@email.com",
						"1234567Abc",
						"012345678" + i
				);
				User user = userService.registerUser(registerForm);

				CreateStudentProfileForm cspForm = new CreateStudentProfileForm(
						user.getId(),
						"CS",
						"Woodsworth",
						2020
				);
				StudentProfile studentProfile = studentProfileService.createStudentProfile(cspForm);
			}

		}

		@Test
		@Order(1)
		public void matchByHabits_desc_expectSuccess() throws Exception {

		}

		@Test
		@Order(1)
		public void matchByHabits_asc_expectSuccess() throws Exception {

		}

		@Test
		@Order(2)
		public void matchByCourses_desc_expectSuccess() throws Exception {
			List<String> targetCourses4 = List.of("a", "b", "c", "d");

			List<String> descCourses1 = List.of("a", "b", "c");
			List<String> descCourses2 = List.of("a", "b");

			List<String> descCourses35 = List.of("a", "b", "c", "d");
		}

		@Test
		@Order(2)
		public void matchByCourses_asc_expectSuccess() throws Exception {
			List<String> targetCourses4 = List.of("a", "b", "c", "d");

			List<String> descCourses1 = List.of("a", "b", "c");
			List<String> descCourses2 = List.of("a", "b");

			List<String> descCourses35 = List.of("a", "b", "c", "d");
		}

		@Test
		@Order(3)
		public void matchByHabitsAndCourses_expectSuccess() throws Exception {

		}

	}

}

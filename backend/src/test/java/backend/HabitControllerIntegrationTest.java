package backend;

import backend.form.HabitForm.*;
import backend.form.StudentProfileForm.*;
import backend.form.UserForm.*;
import backend.model.Habit;
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

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class HabitControllerIntegrationTest extends ControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ResourceLoader resourceLoader;

	@Autowired
	private HabitService habitService;

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;


	@Autowired
	Logger logger;


	@Autowired
	private StudentProfileService studentProfileService;


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
	public void createHabit_expectSuccess() throws Exception {
		CreateHabitForm createHabitForm = new CreateHabitForm(
				1L,
				4,
				5
		);

		Habit habit = habitService.createHabit(createHabitForm);
		Long habitId = habit.getId();

		mockMvc.perform(MockMvcRequestBuilders.post("/api/habit/create")
						.contentType(MediaType.APPLICATION_JSON)
						.content(toJsonString(createHabitForm)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.talkative", is(4)))
				.andExpect(jsonPath("$.collaborative", is(5)));
	}
}

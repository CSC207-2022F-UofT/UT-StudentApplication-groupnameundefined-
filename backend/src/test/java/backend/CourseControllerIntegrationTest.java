package backend;

import backend.form.CourseForm.*;
import backend.service.CourseService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Order(1)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CourseControllerIntegrationTest extends ControllerIntegrationTest {

	@Autowired
	Logger logger;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private CourseService courseService;

	@Test
	@Order(1)
	public void loadCourses_expectSuccess() throws Exception {
		List<String> courseCodes = Arrays.asList(
				"CSC207H1", "CSC236H1", "STA257H1", "PHY224H1", "PHY254H1", "CSC304H1",
				"CSC343H1", "CSC309H1", "CSC311H1", "CSC318H1"
		);

		LoadCoursesForm input1 = new LoadCoursesForm(
				false,
				courseCodes
		);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/course/load-courses")
						.contentType(MediaType.APPLICATION_JSON)
						.content(toJsonString(input1)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[*].code", hasItems(courseCodes.toArray())));
	}

}

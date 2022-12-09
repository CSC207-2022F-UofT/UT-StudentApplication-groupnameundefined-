package backend;

import backend.dto.FriendRequestDto;
import backend.form.FriendRequestForm.*;
import backend.form.UserForm;
import backend.model.User;
import backend.service.UserService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * This class contains all the test cases for controllers of FriendRequest.
 */

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class FriendRequestControllerIntegrationTest extends ControllerIntegrationTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserService userService;


	/**
	 * Test if createFriendRequest successfully create a FriendRequest with correct information.
	 * Expect success.
	 */
	@Test
	@Order(1)
	public void createFriendRequest_expectSuccess() throws Exception {

		UserForm.RegisterForm registerForm1 = new UserForm.RegisterForm(
				"Test Name1",
				"test.name1@email.com",
				"1234567Abc",
				"0123456789"
		);
		UserForm.RegisterForm registerForm2 = new UserForm.RegisterForm(
				"Test Name2",
				"test.name2@email.com",
				"1234567Abc",
				"0123456788"
		);
		UserForm.RegisterForm registerForm3 = new UserForm.RegisterForm(
				"Test Name3",
				"test.name3@email.com",
				"1234567Abc",
				"0123456787"
		);

		// Create 3 Users
		User user1 = userService.registerUser(registerForm1);
		Long userId1 = user1.getId();
		User user2 = userService.registerUser(registerForm2);
		Long userId2 = user2.getId();
		User user3 = userService.registerUser(registerForm3);
		Long userId3 = user3.getId();

		CreateFriendRequestForm input1 = new CreateFriendRequestForm(
				userId1, userId2, ""
		);
		CreateFriendRequestForm input2 = new CreateFriendRequestForm(
				userId1, userId3, ""
		);

		// Create FriendRequests and test.
		// Test if the first FriendRequest (with input1) is created successfully with correct values
		mockMvc.perform(MockMvcRequestBuilders.post("/api/friend-request/create")
						.contentType(MediaType.APPLICATION_JSON)
						.content(toJsonString(input1)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.from.id", is(userId1.intValue())))
				.andExpect(jsonPath("$.to.id", is(userId2.intValue())))
				.andExpect(jsonPath("$.message", is(emptyString())))
				.andExpect(jsonPath("$.status", is("PENDING")));

		// Test if the second FriendRequest (with input2) is created successfully
		mockMvc.perform(MockMvcRequestBuilders.post("/api/friend-request/create")
						.contentType(MediaType.APPLICATION_JSON)
						.content(toJsonString(input2)))
				.andExpect(status().isOk());

	}

	/**
	 * Test if createFriendRequest will fail if the fromUser and toUser does not exist.
	 * Expect EntityDoesNotExist.
	 */
	@Test
	@Order(2)
	public void createFriendRequest_expectEntityDoesNotExist_User() throws Exception {
		CreateFriendRequestForm input = new CreateFriendRequestForm(
				99L, 99L, ""
		);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/friend-request/create")
						.contentType(MediaType.APPLICATION_JSON)
						.content(toJsonString(input)))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.code", is("NOTFOUND-USER")));
	}

	/**
	 * Test if getFriendRequestByFromId can successfully return all FriendRequests with given fromId.
	 * Expect successes.
	 */
	@Test
	@Order(3)
	public void getFriendRequestByFromId_expectSuccess() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/api/friend-request/from/{fromId}", 1))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].id", is(1)));
	}

	/**
	 * Test if getFriendRequestByToId can successfully return all FriendRequests with given toId.
	 * Expect successes.
	 */
	@Test
	@Order(4)
	public void getFriendRequestByToId_expectSuccess() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/api/friend-request/to/{toId}", 1)
						.param("status", "PENDING"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(0)));
	}

	/**
	 * Test if approveFriendRequest can successfully approve the FriendRequest with given id.
	 * Expect successes.
	 */
	@Test
	@Order(5)
	public void approveFriendRequest_expectSuccess() throws Exception {

		// Check if the request is approved
		MvcResult result =
				mockMvc.perform(MockMvcRequestBuilders.get("/api/friend-request/approve/{id}", 1))
						.andExpect(status().isOk())
						.andExpect(jsonPath("$.status", is("APPROVED"))).andReturn();

		FriendRequestDto resultDto = jsonStringToObject
				(result.getResponse().getContentAsString(), FriendRequestDto.class);
		// Check if the toUser is added to fromUser's Friends List
		mockMvc.perform(MockMvcRequestBuilders.get("/api/user/{id}/friends", 1))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[-1].id", is(resultDto.getTo().getId().intValue())));

		// Check if the fromUser is added to toUser's Friends List
		mockMvc.perform(MockMvcRequestBuilders.get("/api/user/{id}/friends", 2))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[-1].id", is(resultDto.getFrom().getId().intValue())));
	}

	/**
	 * Test if denyFriendRequest can successfully deny the FriendRequest with given id.
	 * Expect successes.
	 */
	@Test
	@Order(6)
	public void denyFriendRequest_expectSuccess() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/api/friend-request/deny/{id}", 2))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.status", is("DENIED")));
	}

	/**
	 * Test if denyFriendRequest will fail if the FriendRequest of given id has been processed already.
	 * Expect BadRequest.
	 */
	@Test
	@Order(7)
	public void denyFriendRequest_expectBadRequest() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/api/friend-request/deny/{id}", 2))
				.andExpect(status().isBadRequest());
	}

	/**
	 * Test if deleteFriendRequest can successfully delete the FriendRequest with given id.
	 * Expect successes.
	 */
	@Test
	@Order(8)
	public void deleteFriendRequest_expectSuccess() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/api/friend-request/delete/{id}", 1))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", is(1)));

	}

}

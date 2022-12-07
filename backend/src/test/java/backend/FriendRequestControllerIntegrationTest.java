package backend;

import backend.form.FriendRequestForm.*;
import backend.form.StudentProfileForm;
import backend.form.UserForm;
import backend.model.User;
import backend.service.FriendRequestService;
import backend.service.UserService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static backend.ControllerIntegrationTest.toJsonString;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Order(3)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FriendRequestControllerIntegrationTest extends ControllerIntegrationTest{
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FriendRequestService friendRequestService;

    @Autowired
    private UserService userService;

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

        User user1 = userService.registerUser(registerForm1);
        Long userId1 = user1.getId();
        User user2 = userService.registerUser(registerForm2);
        Long userId2 = user2.getId();
        User user3 = userService.registerUser(registerForm3);
        Long userId3 = user2.getId();

        CreateFriendRequestForm input1 = new CreateFriendRequestForm(
                userId1, userId2, ""
        );
        CreateFriendRequestForm input2 = new CreateFriendRequestForm(
                userId1, userId3, ""
        );

        mockMvc.perform(MockMvcRequestBuilders.post("/api/friend-request/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJsonString(input1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.from.id", is(userId1.intValue())))
                .andExpect(jsonPath("$.to.id", is(userId2.intValue())))
                .andExpect(jsonPath("$.message", is(emptyString())))
                .andExpect(jsonPath("$.status", is("PENDING")));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/friend-request/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJsonString(input2)))
                .andExpect(status().isOk());

    }

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

    @Test
    @Order(3)
    public void getFriendRequestByFromId_expectSuccess() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/friend-request/from/{fromId}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)));
    }

    @Test
    @Order(4)
    public void getFriendRequestByToId_expectSuccess() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/friend-request/to/{toId}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    @Order(5)
    public void approveFriendRequest_expectSuccess() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/friend-request/approve/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("APPROVED")));
    }

    @Test
    @Order(6)
    public void denyFriendRequest_expectSuccess() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/friend-request/deny/{id}", 2))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("DENIED")));
    }

    @Test
    @Order(7)
    public void denyFriendRequest_expectBadRequest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/friend-request/deny/{id}", 2))
                .andExpect(status().isBadRequest());
    }



    @Test
    @Order(8)
    public void deleteFriendRequest_expectSuccess() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/friend-request/delete/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(1)));

    }


}

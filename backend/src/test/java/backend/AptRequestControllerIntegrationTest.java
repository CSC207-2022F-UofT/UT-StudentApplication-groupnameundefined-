package backend;

import backend.dto.AptRequestDto;
import backend.form.AptRequestForm.*;
import backend.form.AptRequestForm;
import backend.form.StudentProfileForm.*;
import backend.form.UserForm;
import backend.model.*;
import backend.service.AptRequestService;
import backend.service.StudentProfileService;
import backend.service.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Order(4)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AptRequestControllerIntegrationTest extends ControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Autowired
    private StudentProfileService studentProfileService;

    @Test
    @Order(1)
    public void createAptRequest_expectSuccess() throws Exception {

        for (int i = 1; i < 4; i++) {
            UserForm.RegisterForm registerForm = new UserForm.RegisterForm(
                    "Test Name" + i,
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


        // Create AptRequests
        CreateAptRequestForm input1 = new CreateAptRequestForm(
                1L, 2L, "APT: user1 to user2", "BA",
                1, 46800000, 1, 50400000, "WEEKLY", "ONCE_A_WEEK"
        );
        CreateAptRequestForm input2 = new CreateAptRequestForm(
                1L, 3L, "APT: user2 to user3", "RB",
                1, 61200000, 1, 64800000, "WEEKLY", "ONCE_A_WEEK"
        );

        mockMvc.perform(MockMvcRequestBuilders.post("/api/apt-request/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJsonString(input1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.from.id", is(1)))
                .andExpect(jsonPath("$.to.id", is(2)))
                .andExpect(jsonPath("$.message", is("APT: user1 to user2")))
                .andExpect(jsonPath("$.status", is("PENDING")))
                .andExpect(jsonPath("$.statDay", is(1)))
                .andExpect(jsonPath("$.endDay", is(1)))
                .andExpect(jsonPath("$.startMil", is(46800000)))
                .andExpect(jsonPath("$.endMil", is(50400000)));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/apt-request/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJsonString(input2)))
                .andExpect(status().isOk());
    }

    @Test
    @Order(2)
    public void createAptRequest_expectEntityDoesNotExist_User() throws Exception {
        AptRequestForm.CreateAptRequestForm input = new AptRequestForm.CreateAptRequestForm(
                99L, 99L, "", "AC",
                1, 46800000, 1, 50400000, "WEEKLY", "ONCE_A_WEEK"
        );

        mockMvc.perform(MockMvcRequestBuilders.post("/api/apt-request/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJsonString(input)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code", is("NOTFOUND-USER")));
    }


    /////////////////////////
    @Test
    @Order(3)
    public void getAptRequestByFromId_expectSuccess() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/apt-request/from/{fromId}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)));
    }

    @Test
    @Order(4)
    public void getAptRequestByToId_expectSuccess() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/apt-request/to/{toId}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    @Order(5)
    public void approveAptRequest_expectSuccess() throws Exception {

        // Check if the request is approved
        MvcResult result =
                mockMvc.perform(MockMvcRequestBuilders.get("/api/apt-request/approve/{id}", 1))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.status", is("APPROVED"))).andReturn();

        AptRequestDto resultDto = jsonStringToObject
                (result.getResponse().getContentAsString(), AptRequestDto.class);

        User fromUser = userService.getUserById(resultDto.getFrom().getId());
        Timetable fromTimetable = fromUser.getStudentProfile().getTimetable();
        List<Long> fromBlockIds = fromTimetable.getBlocks().stream().map(Block::getId).toList();
        User toUser = userService.getUserById(resultDto.getTo().getId());
        Timetable toTimetable = toUser.getStudentProfile().getTimetable();
        List<Long> toBlockIds = toTimetable.getBlocks().stream().map(Block::getId).toList();

        // Check if the apt is added to fromUser's timetable
        assertThat(fromBlockIds, hasItem(resultDto.getAptBlock().getId()));

        // Check if the apt is added to toUser's timetable
        assertThat(toBlockIds, hasItem(resultDto.getAptBlock().getId()));
    }

    @Test
    @Order(6)
    public void denyAptRequest_expectSuccess() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/apt-request/deny/{id}", 2))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("DENIED")));
    }

    @Test
    @Order(7)
    public void denyAptRequest_expectBadRequest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/apt-request/deny/{id}", 2))
                .andExpect(status().isBadRequest());
    }


    @Test
    @Order(8)
    public void deleteAptRequest_expectSuccess() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/apt-request/delete/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(1)));

    }


}

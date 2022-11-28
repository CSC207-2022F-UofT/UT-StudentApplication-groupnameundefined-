package backend.habit;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;

import backend.controller.UserController;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import backend.controller.HabitController;

public class HabitControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private HabitController habitController;

    @Test
    public void contextLoads() throws Exception {
        assertThat(habitController).isNotNull();
    }

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/api/habit/notification")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Habits got.")));
    }



}

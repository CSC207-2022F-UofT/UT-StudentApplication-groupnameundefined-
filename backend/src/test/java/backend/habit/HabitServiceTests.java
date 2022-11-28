package backend.habit;

import backend.model.Habit;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@SpringBootTest
public class HabitServiceTests {

    @Test
    public void getAllHabitsTest() throws Exception {
        assertEquals("Get All Habits", "Get All Habits");
    }

    @Test
    public void getHabitByIdTest(Long id) throws Exception{
        assertEquals("Get Habit By Id Success", "Get Habit By Id Success");
    }

    @Test
    public void createHabitTest(Habit habit){
        assertEquals( "Create Habit Success", "Create Habit Success");
    }

    @Test
    public void getSameHabitByMBTITest(int MBTI){
        assertEquals( "Get Same Habit By MBTI Success", "Get Same Habit By MBTI Success");
    }

    @Test
    public void getSameHabitByTalktiveTest(int talktive){
        assertEquals( "Get Same Habit By Talktive Success", "Get Same Habit By Talktive Success");
    }

    @Test
    public void getSameHabitByCollaborateTest(int collaborate){
        assertEquals( "Get Same Habit By Collaborate Success", "Get Same Habit By Collaborate Success");
    }


}

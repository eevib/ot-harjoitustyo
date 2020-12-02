package ajankayttosovellus;

import ajankayttosovellus.domain.Calender;
import ajankayttosovellus.domain.CalenderService;
import ajankayttosovellus.domain.Todo;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CalenderServiceTest {

    CalenderService calenderService;

    public CalenderServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {

    }

    @Before
    public void setUp() {
        this.calenderService = new CalenderService();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void createTodoAddsTodoToList() {
        this.calenderService.createTodo("Todo");
        int answer = 1;
        assertEquals(this.calenderService.getCalenderSize(), answer);
    }

    @Test
    public void scheduleTodoReturnsTrueWHenSchedulingWorks() {
        calenderService.createTodo("Studies");
        Todo todo = this.calenderService.getLastTodo();
        assertEquals(this.calenderService.scheduleTodo("Mon", "11", todo), true);

    }
}

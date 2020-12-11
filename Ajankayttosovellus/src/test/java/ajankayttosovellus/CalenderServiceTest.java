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
 //       this.calenderService = new CalenderService();
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

    @Test
    public void getCalenderSizeReturnsRight() {
        calenderService.createTodo("todo");
        calenderService.createTodo("todo");
        assertEquals(this.calenderService.getCalenderSize(), 2);
    }

    @Test
    public void scheduleTodoReturnsFalseWhenTimeIsTaken() {
        calenderService.createTodo("todo");
        Todo todo = calenderService.getLastTodo();
        calenderService.scheduleTodo("0", "11", todo);
        calenderService.createTodo("anotherTodo");
        Todo anotherTodo = calenderService.getLastTodo();
        assertEquals(this.calenderService.scheduleTodo("0", "11", anotherTodo), false);
    }
    @Test
    public void getUnScheduledTodosReturnsRightSizedList() {
        calenderService.createTodo("todo");
        assertEquals(this.calenderService.getUnScheduledTodos().size(), 1);
    }
}

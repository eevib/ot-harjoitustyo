package ajankayttosovellus;

import ajankayttosovellus.domain.Todo;
import ajankayttosovellus.domain.Calender;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TodoTest {

    Calender calender;

    public TodoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.calender = new Calender("calender");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void createTodo() {
        Todo todo = new Todo("clean the house", calender.getTodoIdCalc());
        String answer = todo.toString();
        assertEquals("Todo: clean the house. Id: 1", answer);
    }

}

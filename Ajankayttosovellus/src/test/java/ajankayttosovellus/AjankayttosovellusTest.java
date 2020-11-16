package ajankayttosovellus;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class AjankayttosovellusTest {

    Calender calender;

    public AjankayttosovellusTest() {
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
        Todo todo = new Todo("clean the house ");
        calender.addTodo(todo);
        String answer = this.calender.toString();
        assertEquals("Calender calender has 1 todo(s).", answer);
    }
}

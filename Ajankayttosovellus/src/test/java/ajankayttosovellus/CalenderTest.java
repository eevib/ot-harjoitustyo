package ajankayttosovellus;

import ajankayttosovellus.domain.Calender;
import ajankayttosovellus.domain.Todo;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CalenderTest {

    Calender calender;

    public CalenderTest() {

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
    public void addTodoToList() {
        Todo todo = new Todo("Clean the house", 1);
        calender.addTodoToList(todo);
        String answer = "Todo: Clean the house. Id: 1";
        String print = calender.getUnScheduledTodo(1).toString();
        assertEquals(print, answer);

    }

    @Test
    public void schdeuledTodoIsPossibleWhenTimeIsFree() {
        Todo todo = new Todo("Clean the house", 1);
        boolean answer = true;
        assertEquals(calender.scheduleTodo("0", "11", todo), answer);

    }

    @Test
    public void scheduleTodoIsNotPossibleWhenTimeIsNotFree() {
        Todo todo = new Todo("Clean the house", 1);
        Todo todo2 = new Todo("Go grocery shopping", 2);
        calender.scheduleTodo("0", "12", todo);
        Boolean answer = false;
        assertEquals(calender.scheduleTodo("0", "12", todo2), answer);
    }

    @Test
    public void reserveTimeIsPossibleWhenTimeIsFree() {
        Boolean answer = true;
        assertEquals(calender.reserveTimeSlot("1", "13"), answer);
    }

    @Test
    public void reserveTimeIsNotPossibleWhenTimeIsNotFree() {
        Todo todo = new Todo("Clean the house", 1);
        calender.scheduleTodo("0", "12", todo);
        Boolean answer = false;
        assertEquals(calender.reserveTimeSlot("0", "12"), answer);
    }

    @Test
    public void getTodoReturnsRightId() {
        Todo todo = new Todo("Study", 2);
        this.calender.addTodoToList(todo);
        String answer = "Todo: Study. Id: 2";
        assertEquals(calender.getTodo(2).toString(), answer);
    }

}

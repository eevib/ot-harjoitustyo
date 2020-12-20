package ajankayttosovellus.domain;

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

    
    @Before
    public void setUp() {
        this.calender = new Calender("calender");
    }

    @Test
    public void addTodoToList() {
        Todo todo = new Todo("Clean the house", 1);
        calender.addTodoToList(todo);
        int answer = calender.todos.size();
        assertEquals(1, answer);
    }

    @Test
    public void schdeuledTodoIsPossibleWhenTimeIsFree() {
        Todo todo = new Todo("Clean the house", 1);
        assertTrue(calender.scheduleTodo("0", "11", todo));
    }

    @Test
    public void scheduleTodoIsNotPossibleWhenTimeIsNotFree() {
        Todo todo = new Todo("Clean the house", 1);
        Todo todo2 = new Todo("Go grocery shopping", 2);
        calender.scheduleTodo("0", "12", todo);
        assertFalse(calender.scheduleTodo("0", "12", todo2));
    }

    @Test
    public void reserveTimeIsPossibleWhenTimeIsFree() {
        assertTrue(calender.reserveTimeSlot("1", "13"));
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
        int answer = 2;
        assertEquals(calender.getTodo(2).id, answer);
    }

}

package ajankayttosovellus.domain;

import ajankayttosovellus.domain.Todo;
import ajankayttosovellus.domain.Calender;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TodoTest {
    Todo todo;

    @Before
    public void setUp() {
        this.todo = new Todo("todo", 1);
    }

    @Test
    public void createTodoCreatesTodo() {
        Todo todo2 = new Todo("clean the house", 2);
        String answer = todo2.getTodoName();
        assertEquals(answer, "clean the house");
    }

    @Test
    public void getNameReturnsName() {
        assertEquals("todo", todo.getTodoName());
    }

    @Test
    public void getTodoIdReurnsId() {

    }

}

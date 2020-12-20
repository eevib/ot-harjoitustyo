package ajankayttosovellus.domain;

import ajankayttosovellus.domain.Calender;
import ajankayttosovellus.domain.CalenderService;
import ajankayttosovellus.domain.Todo;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CalenderServiceTest {

    FakeUserDao userDao;
    FakeCalenderDao calenderDao;
    CalenderService calenderService;

    @Before
    public void setUp() {
        calenderDao = new FakeCalenderDao();
        userDao = new FakeUserDao();
        Calender calender = new Calender("calender");
        User user1 = new User("testUser", "password");
        User user2 = new User("testUser2", "password2");
        calender.setUser(user1);
        userDao.create(user1);
        userDao.create(user2);
        calenderDao.create(calender);
        calenderService = new CalenderService(calenderDao, userDao);
        calenderService.setCalender(calender);
    }

    @Test
    public void createTodoAddsTodoToList() {
        this.calenderService.createTodo("Todo");
        int answer = 1;
        List<Todo> todos = this.calenderService.getUnScheduledTodos();
        assertEquals(answer, todos.size());
    }

    @Test
    public void scheduleTodoReturnsTrueWhenSchedulingWorks() {
        calenderService.createTodo("Studies");
        Todo todo = this.calenderService.getLastTodo();
        assertTrue(this.calenderService.scheduleTodo("Mon", "11", todo));
    }

    @Test
    public void getCalenderSizeReturnsRight() {
        calenderService.createTodo("todo");
        calenderService.createTodo("todo");
        assertEquals(this.calenderService.getUnchedueldTodosSize(), 2);
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
    public void unScheduledTodosReturnsRightSizedList() {
        calenderService.createTodo("todo");
        assertEquals(this.calenderService.getUnScheduledTodos().size(), 1);
    }

    @Test
    public void createdUserIsPutAsLoggedUser() {
        calenderService.createUser("user", "password");
        User loggedUser = calenderService.getLoggedUser();
        assertEquals("user", loggedUser.getName());
    }

    @Test
    public void existingUserIsNotCreatedAgain() {
        assertFalse(calenderService.createUser("testUser", "password"));
    }

    @Test
    public void rightTodoIsReturned() {
        calenderService.createTodo("todo");
        assertEquals("todo", calenderService.getTodo(1).getTodoName());
    }

    @Test
    public void incorrectTimeIsNotAccepted() {
        assertFalse(calenderService.checkTime("25"));
    }

    @Test
    public void existingCalenderIsFound() {
        calenderService.login("testUser", "password");
        Calender calender = calenderService.getCalender();
        assertEquals("calender", calender.getCalenderName());
    }

}

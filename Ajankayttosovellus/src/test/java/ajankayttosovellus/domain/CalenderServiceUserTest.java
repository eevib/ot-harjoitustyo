package ajankayttosovellus.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CalenderServiceUserTest {

    FakeUserDao userDao;
    FakeCalenderDao calenderDao;
    CalenderService calenderService;

    public CalenderServiceUserTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        calenderDao = new FakeCalenderDao();
        userDao = new FakeUserDao();
        this.calenderService = new CalenderService(calenderDao, userDao);

    }

    @After
    public void tearDown() {
    }

    @Test
    public void existingUserCanLogIn() {
        boolean answer = calenderService.login("testUser", "password");
        System.out.println(answer);
        assertTrue(answer);

        String user = calenderService.getLoggedUser().getName();
        assertEquals("testUser", user);
    }

    @Test
    public void nonExistingUserCanNotLogIn() {
        boolean answer = calenderService.login("noUser", "password");
        assertFalse(answer);
    }
    @Test
    public void existingUserAndWrongPasswordCanNotLogin() {
        boolean answer = calenderService.login("testUser", "wrongPassword");
        assertFalse(answer);
    }

    @Test
    public void cantLogInWrongPasswordExistingUser() {
        boolean answer = calenderService.login("testUser", "wrongPassword");
        assertFalse(answer);
    }
}

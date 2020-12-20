package ajankayttosovellus.dao;

import ajankayttosovellus.dao.CalenderDao;
import ajankayttosovellus.domain.Calender;
import ajankayttosovellus.domain.FakeCalenderDao;
import ajankayttosovellus.domain.User;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

public class FileCalenderDaoTest {

    @Rule
    public TemporaryFolder testfolder = new TemporaryFolder();

    File calenderFile;
    CalenderDao dao;

    @Before
    public void setUp() throws Exception {
        calenderFile = this.testfolder.newFile("testfile_calender.txt");

        try ( FileWriter file = new FileWriter(calenderFile.getAbsolutePath())) {
            file.write("testUser,password:todo; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; ; \n");
        }
        dao = new FileCalenderDao(calenderFile.getAbsolutePath());
    }

    @Test
    public void userIsFindByName() {
        User testUser = new User("testUser", "password");
        Calender calender = dao.findByUser(testUser);

        assertEquals(testUser.getName(), calender.getUser().getName());
    }

    @Test
    public void createdCalenderIsAddedToList() {
        Calender calender = new Calender("test");
        User testUser = new User("testUser2", "password");
        calender.setUser(testUser);
        dao.create(calender);
        List<Calender> calenders = dao.getAll();
        assertEquals(2, calenders.size());

    }

    @Test
    public void calenderIsSavedToFileNewUser() {
        Calender calender = new Calender("test1");
        User testUser = new User("testUser3", "password");
        calender.setUser(testUser);
        dao.saveCalender(calender);

        assertEquals("testUser3", dao.findByUser(testUser).getUser().getName());
    }

    @Test
    public void calenderIsSavedToFileExcistingUser() {
        User user = new User("testUser", "password");
        dao.saveCalender(dao.findByUser(user));
        assertEquals("testUser", dao.findByUser(user).getUser().getName());
    }
    @Test
    public void nonExistingCalenderIsNotFound() {
        User user = new User("user", "p");
        Calender calender = dao.findByUser(user);
        assertEquals(null, calender);
    }

    @After
    public void tearDown() {
        calenderFile.delete();
    }

}

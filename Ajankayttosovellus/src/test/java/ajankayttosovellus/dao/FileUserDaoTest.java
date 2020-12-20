package ajankayttosovellus.dao;

import ajankayttosovellus.domain.User;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

public class FileUserDaoTest {

    @Rule
    public TemporaryFolder testfolder = new TemporaryFolder();

    File userFile;
    UserDao dao;


    @Before
    public void setUp() throws IOException, Exception {
        userFile = testfolder.newFile("testfile_user.txt");

        try ( FileWriter file = new FileWriter(userFile.getAbsolutePath())) {
            file.write("testUser,password \n");
        }
        dao = new FileUserDao(userFile.getAbsolutePath());
    }
    @Test
    public void existingUserIsFound() {
        assertEquals("password ", dao.findByName("testUser").getPassword());
    }
    @Test
    public void nonExistingUserIsNotFound() {
        User user = dao.findByName("noName");
        assertEquals(null, user);
    }

    @Test
    public void savedUsersIsAddedToList() {
        User user = new User("name", "password");
        dao.create(user);
        assertEquals(2, dao.getAll().size());
    }
    @After
    public void tearDown() {
        userFile.delete();
    }

}

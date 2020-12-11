package ajankayttosovellus.dao;

import ajankayttosovellus.domain.User;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileUserDao implements UserDao {

    private List<User> users;
    private String file;
    private FileWriter writer;

    public FileUserDao(String file) throws Exception {
        this.file = file;
        this.users = new ArrayList<>();
     //   createFile(file);
        loadUsers();

    }

//    private void createFile(String fileName) throws Exception {
//        this.file = fileName;
//        try {
//            this.writer = new FileWriter(new File(this.file));
//        } catch (Exception exception) {
//            exception.printStackTrace();
//        }
//    }

    private void loadUsers() throws Exception {
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(",");
                User user = new User(parts[0], parts[1]);
                users.add(user);
            }
        } catch (Exception exception) {
            FileWriter writer = new FileWriter(new File(this.file));
            writer.close();
        }
    }

    private void saveUsers() throws Exception {
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (User user : users) {
                writer.write(user.getName() + "," + user.getPasswod() + "\n");
                
            }
            writer.close();
        } catch (Exception exception) {
            exception.printStackTrace();

        }
    }

    @Override
    public User findByName(String userName) {
        if (users.isEmpty()) {
            return null;
        }
        System.out.println(userName);
        return users.stream().filter(user -> user.getName().equals(userName)).findFirst().orElse(null);
    }

    @Override
    public User create(User user) {
        users.add(user);
        try {
            saveUsers();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        return this.users;
    }

}

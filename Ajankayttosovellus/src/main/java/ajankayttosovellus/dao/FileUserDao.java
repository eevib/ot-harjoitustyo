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

    public FileUserDao(String file) {
        this.file = file;
        createFile(file);
        users = new ArrayList<>();
        loadUsers();

    }

    private void createFile(String fileName) {
        try {
            this.writer = new FileWriter(new File(this.file));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void loadUsers() {
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(",");
                User user = new User(parts[0], parts[1]);
                users.add(user);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void saveUsers() {
        try {
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
        if(users.isEmpty()) {
            return null;
        }
        return users.stream().filter(user -> user.getName().equals(userName)).findFirst().orElse(null);
    }

    @Override
    public User create(User user) {
        users.add(user);
        saveUsers();
        return user;
    }

    @Override
    public List<User> getAll() {
        return this.users;
    }

}

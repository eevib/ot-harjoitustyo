package ajankayttosovellus.domain;

import ajankayttosovellus.dao.UserDao;
import java.util.ArrayList;
import java.util.List;

public class FakeUserDao implements UserDao {

    private List<User> users;

    public FakeUserDao() {
        this.users = new ArrayList<>();
        this.users.add(new User("testUser", "password"));
    }

    @Override
    public User create(User user) {
        users.add(user);
        return user;
    }

    @Override
    public List<User> getAll() {
        return this.users;
    }

    @Override
    public User findByName(String name) {
        if (users.isEmpty()) {
            return null;
        }
        return users.stream().filter(user -> user.getName().equals(name)).findFirst().orElse(null);
    }

}

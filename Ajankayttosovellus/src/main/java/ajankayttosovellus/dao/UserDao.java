
package ajankayttosovellus.dao;

import ajankayttosovellus.domain.User;
import java.util.List;

public interface UserDao {
    
    User create(User user);    
    List<User> getAll();
    User findByName(String name);
}

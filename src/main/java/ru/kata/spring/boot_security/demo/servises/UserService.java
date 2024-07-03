package ru.kata.spring.boot_security.demo.servises;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User findByUsername(String username);
    void deleteUser(long id);
    User getUser(long id);
    boolean updateUser(User user, List<Role> roles);
    void save (User user,List<Role> roles);
}

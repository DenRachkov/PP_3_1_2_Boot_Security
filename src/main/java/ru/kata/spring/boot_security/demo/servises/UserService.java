package ru.kata.spring.boot_security.demo.servises;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    void deleteUser(long id);
    User getUser(long id);
    void updateUser(User user, Long id);
    void save (User user);

    User findByUsername(String firstName);

}

package ru.kata.spring.boot_security.demo.servises;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface AdminService {
    List<User> getAllUsers();
    void deleteUser(long id);
    User getUser(long id);
    void updateUser(User user, List<Role> roles, Long id);
    void save (User user,List<Role> roles);
}

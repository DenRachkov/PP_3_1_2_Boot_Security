package ru.kata.spring.boot_security.demo.servises;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {

    User findByUsername(String username);

}

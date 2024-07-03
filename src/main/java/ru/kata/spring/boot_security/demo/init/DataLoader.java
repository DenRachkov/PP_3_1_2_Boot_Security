package ru.kata.spring.boot_security.demo.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;
import ru.kata.spring.boot_security.demo.servises.UserServiceImpl;

import java.util.Collections;

@Component
public class DataLoader implements CommandLineRunner {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserServiceImpl userServiceImpl;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(UserRepository userRepository, RoleRepository roleRepository, @Lazy UserServiceImpl userServiceImpl, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userServiceImpl = userServiceImpl;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        createRolesIfNotExist();
        addUser(new User("Admin", "root", roleRepository.findAllById(Collections.singleton(1L))));
        addUser(new User("User", "root", roleRepository.findAllById(Collections.singleton(2L))));


    }
    public boolean addUser(User user) {
        User userDB = userRepository.findByUsername(user.getUsername());
        if (userDB != null) {
            return false;
        }
        createRolesIfNotExist();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }
    public void createRolesIfNotExist() {
        if (roleRepository.findByName("ROLE_ADMIN").isEmpty()) {
            roleRepository.save(new Role(1L, "ROLE_ADMIN"));
        }
        if (roleRepository.findByName("ROLE_USER").isEmpty()){
            roleRepository.save(new Role(2L, "ROLE_USER"));
        }
    }



}

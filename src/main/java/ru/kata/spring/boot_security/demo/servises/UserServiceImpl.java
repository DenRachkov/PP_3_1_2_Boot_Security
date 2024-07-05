package ru.kata.spring.boot_security.demo.servises;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
        @Transactional
        public void deleteUser(long id) {
            userRepository.deleteById(id);
        }
        @Override
        public User getUser(long id) {
            userRepository.getById(id);
            return userRepository.getById(id);
        }

        @Override
        @Transactional
        public void updateUser(User user, List<Role> roles, Long id) {
            User userDB = userRepository.getById(id);
            userDB.setUsername(user.getUsername());
            String newPassword = user.getPassword();
            String oldPassword = userRepository.findByPassword(id);

            if (!Objects.equals(newPassword, oldPassword)){
                userDB.setPassword(passwordEncoder.encode(user.getPassword()));
            }

            userDB.setRoles(roles);
            userRepository.save(userDB);
        }

        @Override
        @Transactional
        public void save(User user, List<Role> roles) {
            user.setName(user.getUsername());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(roles);
            userRepository.save(user);
        }

        @Override
        public List<User> getAllUsers() {
            return userRepository.findAll();
        }

    @Override
    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }




}

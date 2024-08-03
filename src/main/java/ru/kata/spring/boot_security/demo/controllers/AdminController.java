package ru.kata.spring.boot_security.demo.controllers;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.servises.RoleService;
import ru.kata.spring.boot_security.demo.servises.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;


    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/user")
    public User snowAdminUser (Model model, Principal principal){
        User user = userService.findByUsername(principal.getName());
        if (user == null){
            throw new UsernameNotFoundException("User not found");
        }
        model.addAttribute("user", user);
        return user;
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserId(@PathVariable long id) {
        final User user = userService.getUser(id);
//        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public List<User> snowAllUsers(Model model, Principal principal) {
        List<User> listUser = userService.getAllUsers();
        User user = userService.findByUsername(principal.getName());
        if (user == null){
            throw new UsernameNotFoundException("User not found");
        }
        model.addAttribute("user", user);
        model.addAttribute("users", listUser);
        model.addAttribute("roles", roleService.getAllRoles());
        return listUser;
    }


    @PatchMapping("/update")
    public ResponseEntity<HttpStatus> updateUser( @RequestBody @Valid User user) {

        userService.updateUser(user, user.getId() );
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @PostMapping("/addUser")
    public ResponseEntity<HttpStatus> addUser(@RequestBody @Valid User user) {
        userService.save(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getRoleList(){
        List<Role> roleList = roleService.getAllRoles();
        return new ResponseEntity<>(roleList, HttpStatus.OK);
    }


}


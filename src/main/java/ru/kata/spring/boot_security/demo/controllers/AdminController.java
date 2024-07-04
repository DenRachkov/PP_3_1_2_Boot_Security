package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.servises.AdminService;
import ru.kata.spring.boot_security.demo.servises.RoleService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;
    private final RoleService roleService;


    @Autowired
    public AdminController(AdminService adminService, RoleService roleService) {
        this.adminService = adminService;

        this.roleService = roleService;
    }

    @GetMapping
    public String snowAllUsers(Model model) {
        List<User> listUser = adminService.getAllUsers();
        model.addAttribute("users", listUser);
        return "/admin";
    }

    @GetMapping("/update")
    public String updateUserForm(Model model, @RequestParam("id") long id) {
        model.addAttribute("user", adminService.getUser(id));
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "/user-update";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") @Valid User user,
                             @RequestParam("id") long id,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/user-update";
        }
        adminService.updateUser(user, user.getRoles(), id);
        return "redirect:/admin";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("id") long id) {
        adminService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/addUser")
    public String addUserForm(Model model, @ModelAttribute("user") User user) {
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "/addUser";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/addUser";
        }
        adminService.save(user, user.getRoles());
        return "redirect:/admin";
    }
}

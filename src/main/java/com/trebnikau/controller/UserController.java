package com.trebnikau.controller;

import com.trebnikau.entity.Role;
import com.trebnikau.entity.User;
import com.trebnikau.repo.UserRepo;
import com.trebnikau.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;


    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userRepo.findAll());
        return "user-list";
    }

    @GetMapping("/{id}")
    public String userEdit(@PathVariable("id") User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "user-edit";
    }

    @PostMapping()
    public String saveUser(@RequestParam("userId") User user,
                           @RequestParam("userName") String userName,
                           @RequestParam Map<String, String> form) {
        userService.saveUserAfterEditing(user, userName, form);
        return "redirect:/user";
    }

}

package com.trebnikau.controller;

import com.trebnikau.entity.Role;
import com.trebnikau.entity.User;
import com.trebnikau.repo.UserRepo;
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
                           @RequestParam Map<String, String> form){
        user.setUsername(userName);
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        // для начала нужно очистить все роли у user
        user.getRoles().clear();
        for (String key : form.keySet()) {
            if (roles.contains(key)){
                user.getRoles().add(Role.valueOf(key));
            }
        }
        // если в чекбоксе небыло выбранно ни одной роли, тогда автоматически присваетвается роль user
        if (user.getRoles().size() == 0){
            user.getRoles().add(Role.USER);
        }
        userRepo.save(user);
        return "redirect:/user";
    }

}

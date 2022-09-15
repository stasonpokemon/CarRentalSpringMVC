package com.trebnikau.controller;

import com.trebnikau.entity.User;
import com.trebnikau.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserService userService;


    @GetMapping()
    public String registration() {
        return "registration";
    }

    @PostMapping()
    public String addRegisteredUser(@RequestParam String passwordConfirmation,
                                    @Valid User user,
                                    BindingResult bindingResult,
                                    Model model) {
        return userService.addRegisteredUser(passwordConfirmation, user, bindingResult, model);
    }

    @GetMapping("/activate/{code}")
    public String activateUser(@PathVariable("code") String activationCode,
                               Model model){
        return userService.activateUser(activationCode, model);
    }
}
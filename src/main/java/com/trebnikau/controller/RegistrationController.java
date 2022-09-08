package com.trebnikau.controller;

import com.trebnikau.entity.Role;
import com.trebnikau.entity.User;
import com.trebnikau.repo.UserRepo;
import com.trebnikau.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@RequestParam String passwordConfirmation,
                          @Valid User user,
                          BindingResult bindingResult,
                          Model model) {


        boolean isPasswordConfirmationEmpty = false;
        if (StringUtils.isEmpty(passwordConfirmation)){
            isPasswordConfirmationEmpty = true;
            model.addAttribute("passwordConfirmationError", "Password confirmation can't be empty");
        }

        boolean isDifferentPasswords = false;
        if (user.getPassword() != null && !passwordConfirmation.equals(user.getPassword())){
            model.addAttribute("passwordError","Passwords are different");
            isDifferentPasswords = true;
        }

        if (bindingResult.hasErrors() || isPasswordConfirmationEmpty){
            Map<String, String> errors = UtilsController.getErrors(bindingResult);
            model.mergeAttributes(errors);
            model.addAttribute("user",user);
            return "registration";
        }else {
            if (isDifferentPasswords){
                model.addAttribute("user",user);
                return "registration";
            }else{
//                int addNewUserResponse = userService.addNewUser(user);
                if (userService.addNewUser(user) == -1){
                    model.addAttribute("usernameError", "User with entered username exist");
                    model.addAttribute("user",user);
                    return "registration";
                }else  if(userService.addNewUser(user) == 0){
                    model.addAttribute("emailError","Entered email is busy");
                    model.addAttribute("user",user);
                    return "registration";
                }else {
                    return "redirect:/login";
                }
            }
        }
    }
}
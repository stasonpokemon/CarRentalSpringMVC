package com.trebnikau.controller;

import com.trebnikau.entity.User;
import com.trebnikau.repo.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private CarRepo carRepo;


    @GetMapping("/")
    public String mainPage(){
        return "main-page";
    }

    @GetMapping("/home")
    public String hello(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("name", user.getUsername());
        model.addAttribute("cars",carRepo.findAll());
        return "home";
    }







}

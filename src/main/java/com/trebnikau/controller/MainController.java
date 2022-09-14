package com.trebnikau.controller;

import com.trebnikau.repo.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private CarRepo carRepo;


    @GetMapping("/")
    public String mainPage(){
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String homePage(Model model){
        model.addAttribute("cars",carRepo.findAll());
        return "home";
    }







}

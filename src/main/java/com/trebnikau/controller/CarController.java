package com.trebnikau.controller;

import com.trebnikau.repo.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarRepo carRepo;

    @GetMapping()
    public String showAllCars(Model model) {
        model.addAttribute("cars", carRepo.findAll());
        return "car-list";
    }
}

package com.trebnikau.controller;

import com.trebnikau.entity.Car;
import com.trebnikau.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/cars")
public class CarController {


    @Autowired
    private CarService carService;

    @GetMapping()
    public String showAllCars(@RequestParam(required = false, name = "filter", defaultValue = "") String filter,
                              @RequestParam(required = false, name = "sort", defaultValue = "") String sort,
                              Model model) {
        return carService.showAllCars(filter, sort, model);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/add")
    public String addMenuCar() {
        return "add-car";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}")
    public String editMenuCar(@PathVariable("id") Car car,
                              Model model) {
        model.addAttribute("car", car);
        return "edit-car";
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/{type}")
    public String saveAddedOrEditedCar(@PathVariable("type") String typeOfRequest,
                                       @Valid Car car,
                                       BindingResult bindingResult,
                                       Model model) {
        return carService.saveAddedOrEditedCar(typeOfRequest, car, bindingResult, model);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/delete/{id}")
    public String deleteCar(@PathVariable("id") Long carId) {
        carService.deleteCar(carId);
        return "redirect:/cars";
    }
}

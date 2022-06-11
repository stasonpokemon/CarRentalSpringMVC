package com.trebnikau.controller;

import com.trebnikau.entity.Car;
import com.trebnikau.repo.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}")
    public String editCar(@PathVariable("id") Car car,
                          Model model){
        model.addAttribute("car",car);
        return "edit-car";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping()
    public String saveCar(@RequestParam("carId") Car car,
                          @RequestParam("carProducer") String carProducer,
                          @RequestParam("carModel") String carModel,
                          @RequestParam("carReleaseDate") String carReleaseDate){
        car.setProducer(carProducer);
        car.setModel(carModel);
        car.setReleaseDate(carReleaseDate);
        carRepo.save(car);
        return "redirect:/cars";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping ("/add")
    public String addCar(){
        return "add-car";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/add")
    public String saveCar(@RequestParam("carProducer") String carProducer,
                          @RequestParam("carModel") String carModel,
                          @RequestParam("carReleaseDate") String carReleaseDate){
        Car car = new Car();
        car.setProducer(carProducer);
        car.setModel(carModel);
        car.setReleaseDate(carReleaseDate);
        carRepo.save(car);
        return "redirect:/cars";
    }


}

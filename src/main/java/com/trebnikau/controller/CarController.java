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
    public String showAllCars(@RequestParam(required = false, name = "filter", defaultValue = "") String filter,
                              Model model) {
        Iterable<Car> cars;
        if (filter != null && !filter.isEmpty()){
            cars = carRepo.findCarsByProducer(filter);
        }else {
           cars = carRepo.findAll();
        }

        model.addAttribute("cars", cars);
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

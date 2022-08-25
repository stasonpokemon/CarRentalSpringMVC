package com.trebnikau.controller;

import com.trebnikau.entity.Car;
import com.trebnikau.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cars")
public class CarController {


    @Autowired
    private CarService carService;

    @GetMapping()
    public String showAllCars(@RequestParam(required = false, name = "filter", defaultValue = "") String filter,
                              @RequestParam(required = false, name = "sort", defaultValue = "") String sort,
                              Model model) {
        Iterable<Car> cars;

        if (sort != null && !sort.isEmpty() && "free".equals(sort)) {
            cars = carService.findCarsByEmploymentStatus();
            model.addAttribute("cars", cars);
            return "car-list";
        } else if (sort != null && !sort.isEmpty() && "price".equals(sort)) {
            cars = carService.sortCarsByPrice();
            model.addAttribute("cars", cars);
            return "car-list";
        }

        if (filter != null && !filter.isEmpty()) {
            cars = carService.findCarsByProducer(filter);
        } else {
            cars = carService.findAll();
        }
        model.addAttribute("cars", cars);
        return "car-list";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}")
    public String editCar(@PathVariable("id") Car car,
                          Model model) {
        model.addAttribute("car", car);
        return "edit-car";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping()
    public String saveCar(@RequestParam("carId") Car car,
                          @RequestParam("carProducer") String carProducer,
                          @RequestParam("carModel") String carModel,
                          @RequestParam("carReleaseDate") String carReleaseDate,
                          @RequestParam("carPricePerDay") String carPricePerDay,
                          @RequestParam("carEmploymentStatus") String carEmploymentStatus,
                          @RequestParam("carDamageStatus") String carDamageStatus
    ) {
        car.setProducer(carProducer);
        car.setModel(carModel);
        car.setReleaseDate(carReleaseDate);
        car.setPricePerDay(Double.parseDouble(carPricePerDay));
        if ("FREE".equals(carEmploymentStatus)) {
            car.setEmploymentStatus(true);
        } else {
            car.setEmploymentStatus(false);
        }
        car.setDamageStatus(carDamageStatus);
        carService.saveCar(car);
        return "redirect:/cars";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/add")
    public String addCar() {
        return "add-car";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/add")
    public String addCar(@RequestParam("carProducer") String carProducer,
                         @RequestParam("carModel") String carModel,
                         @RequestParam("carReleaseDate") String carReleaseDate,
                         @RequestParam("carPricePerDay") String carPricePerDay,
                         @RequestParam("carEmploymentStatus") String carEmploymentStatus,
                         @RequestParam("carDamageStatus") String carDamageStatus) {
        Car car = new Car();
        car.setProducer(carProducer);
        car.setModel(carModel);
        car.setReleaseDate(carReleaseDate);
        car.setPricePerDay(Double.parseDouble(carPricePerDay));
        if ("FREE".equals(carEmploymentStatus)) {
            car.setEmploymentStatus(true);
        } else {
            car.setEmploymentStatus(false);
        }
        car.setDamageStatus(carDamageStatus);
        carService.saveCar(car);
        return "redirect:/cars";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/delete/{id}")
    public String deleteCar(@PathVariable("id") Long carId) {
        System.out.println("1");
        carService.deleteCar(carId);
        System.out.println("2");
        return "redirect:/cars";
    }
}

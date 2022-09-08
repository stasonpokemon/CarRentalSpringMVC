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
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
        Iterable<Car> allCars;

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
//            allCars = carService.findAll();
//            cars = StreamSupport.stream(allCars.spliterator(), false).
//                    filter(car -> car.getProducer().equals(filter)).collect(Collectors.toList());
        } else {
            cars = carService.findAll();
        }
        model.addAttribute("cars", cars);
        model.addAttribute("allCars", cars);
        return "car-list";
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
    @PostMapping()
    public String saveEditCar(@Valid Car car,
                              BindingResult bindingResult,
                              Model model) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = UtilsController.getErrors(bindingResult);
            model.mergeAttributes(errors);
            model.addAttribute("car", car);
            return "edit-car";
        }
        carService.saveCar(car);
        return "redirect:/cars";
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/add")
    public String addNewCar(@Valid Car car,
                            BindingResult bindingResult,
                            Model model) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = UtilsController.getErrors(bindingResult);
            model.mergeAttributes(errors);
            model.addAttribute("car", car);
            return "add-car";
        }
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

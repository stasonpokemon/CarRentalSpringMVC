package com.trebnikau.service;

import com.trebnikau.entity.Car;
import com.trebnikau.repo.CarRepo;
import com.trebnikau.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CarService {

    @Autowired
    private CarRepo carRepo;


    public void saveCar(Car car) {
        carRepo.save(car);
    }

    public void deleteCar(Long carId) {
        carRepo.deleteById(carId);
    }

    public Iterable<Car> findCarsByProducer(String filter) {
        return carRepo.findCarsByProducer(filter);
    }

    public Iterable<Car> findAll() {
        return carRepo.findAll();
    }


    public Iterable<Car> findCarsByEmploymentStatus() {
        return carRepo.findCarsByEmploymentStatus(true);
    }

    public Iterable<Car> sortCarsByPriceFromMin() {

        return StreamSupport.stream(carRepo.findAll().spliterator(), false).
                sorted(Comparator.comparingDouble(Car::getPricePerDay)).
                collect(Collectors.toList());
    }

    public Iterable<Car> sortCarsByPriceFromMax() {

        return StreamSupport.stream(carRepo.findAll().spliterator(), false).
                sorted(Comparator.comparingDouble(Car::getPricePerDay).reversed()).
                collect(Collectors.toList());
    }

    public String showAllCars(String filter, String sort, Model model) {
        // Отдельно список всех машин нужен для селекта поиска
        model.addAttribute("allCars", findAll());

        Iterable<Car> cars;

        if (sort != null && !sort.isEmpty() && "free".equals(sort)) {
            cars = findCarsByEmploymentStatus();
            model.addAttribute("cars", cars);
            return "show-all-cars";
        } else if (sort != null && !sort.isEmpty() && "price_min".equals(sort)) {
            cars = sortCarsByPriceFromMin();
            model.addAttribute("cars", cars);
            return "show-all-cars";
        } else if (sort != null && !sort.isEmpty() && "price_max".equals(sort)) {
            cars = sortCarsByPriceFromMax();
            model.addAttribute("cars", cars);
            return "show-all-cars";
        }

        if (filter != null && !filter.isEmpty()) {
            cars = findCarsByProducer(filter);
        } else {
            cars = findAll();
        }
        model.addAttribute("cars", cars);
        return "show-all-cars";
    }

    public String saveAddedOrEditedCar(String typeOfRequest, Car car, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = Util.getErrors(bindingResult);
            model.mergeAttributes(errors);
            model.addAttribute("car", car);
            if ("edit".equals(typeOfRequest)) {
                return "edit-car";
            } else if ("add".equals(typeOfRequest)) {
                return "add-car";
            }
        }
        saveCar(car);
        return "redirect:/cars";
    }
}

package com.trebnikau.service.impl;

import com.trebnikau.entity.Car;
import com.trebnikau.repo.CarRepo;
import com.trebnikau.service.CarService;
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
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepo carRepo;

    @Override
    public void saveCar(Car car) {
        carRepo.save(car);
    }

    @Override
    public void deleteCar(Car car) {
        car.setDeleted(true);
    }

    @Override
    public Iterable<Car> findCarsByProducer(String filter) {
        return carRepo.findCarsByProducerAndDeleted(filter, false);
    }

    @Override
    public Iterable<Car> findAll() {
        return carRepo.findAll();
    }

    @Override
    public Iterable<Car> findAllIsNotDeleted() {
        return carRepo.findCarsByDeleted(false);
    }

    @Override
    public Iterable<Car> findCarsByEmploymentStatusAndIsNotDeleted() {
        return carRepo.findCarsByEmploymentStatusAndDeleted(true, false);
    }

    @Override
    public Iterable<Car> sortCarsByPriceFromMinIsNotDeleted() {

        return StreamSupport.stream(findAllIsNotDeleted().spliterator(), false).
                sorted(Comparator.comparingDouble(Car::getPricePerDay)).
                collect(Collectors.toList());
    }

    @Override
    public Iterable<Car> sortCarsByPriceFromMaxIsNotDeleted() {

        return StreamSupport.stream(findAllIsNotDeleted().spliterator(), false).
                sorted(Comparator.comparingDouble(Car::getPricePerDay).reversed()).
                collect(Collectors.toList());
    }

    @Override
    public String showAllCars(String filter, String sort, Model model) {
        // Отдельно список всех машин нужен для селекта поиска
        model.addAttribute("allCars", findAllIsNotDeleted());

        Iterable<Car> cars;

        if (sort != null && !sort.isEmpty() && "free".equals(sort)) {
            cars = findCarsByEmploymentStatusAndIsNotDeleted();
            model.addAttribute("cars", cars);
            return "show-all-cars";
        } else if (sort != null && !sort.isEmpty() && "price_min".equals(sort)) {
            cars = sortCarsByPriceFromMinIsNotDeleted();
            model.addAttribute("cars", cars);
            return "show-all-cars";
        } else if (sort != null && !sort.isEmpty() && "price_max".equals(sort)) {
            cars = sortCarsByPriceFromMaxIsNotDeleted();
            model.addAttribute("cars", cars);
            return "show-all-cars";
        }

        if (filter != null && !filter.isEmpty()) {
            cars = findCarsByProducer(filter);
        } else {
            cars = findAllIsNotDeleted();
        }
        model.addAttribute("cars", cars);
        return "show-all-cars";
    }

    @Override
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
        car.setDeleted(false);
        saveCar(car);
        return "redirect:/cars";
    }
}

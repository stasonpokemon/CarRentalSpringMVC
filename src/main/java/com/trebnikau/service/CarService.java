package com.trebnikau.service;

import com.trebnikau.entity.Car;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

public interface CarService {
    void saveCar(Car car);

    void deleteCar(Car car);

    Iterable<Car> findCarsByProducer(String filter);

    Iterable<Car> findAll();

    Iterable<Car> findAllIsNotDeleted();

    Iterable<Car> findCarsByEmploymentStatusAndIsNotDeleted();

    Iterable<Car> sortCarsByPriceFromMinIsNotDeleted();

    Iterable<Car> sortCarsByPriceFromMaxIsNotDeleted();

    String showAllCars(String filter, String sort, Model model);

    String saveAddedOrEditedCar(String typeOfRequest, Car car, BindingResult bindingResult, Model model);
}

package com.trebnikau.service;

import com.trebnikau.entity.Car;
import com.trebnikau.repo.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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

    public Iterable<Car> sortCarsByPrice() {

        List<Car> cars = StreamSupport.stream(carRepo.findAll().spliterator(), false).
                sorted(Comparator.comparingDouble(Car::getPricePerDay)).
                collect(Collectors.toList());
        return cars;
    }
}

package com.trebnikau.repo;

import com.trebnikau.entity.Car;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarRepo extends CrudRepository<Car, Long> {

    List<Car> findCarsByProducer(String producer);
}

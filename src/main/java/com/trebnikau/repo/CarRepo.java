package com.trebnikau.repo;

import com.trebnikau.entity.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarRepo extends CrudRepository<Car, Long> {
}

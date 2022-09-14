package com.trebnikau.repo;

import com.trebnikau.entity.Car;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarRepo extends CrudRepository<Car, Long> {

    List<Car> findCarsByProducerAndDeleted(String producer, boolean deleted);
    List<Car> findCarsByEmploymentStatusAndDeleted(boolean employmentStatus, boolean deleted);
    List<Car> findCarsByDeleted(boolean deleted);
}

package com.trebnikau.repo;

import com.trebnikau.entity.Order;
import com.trebnikau.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepo extends CrudRepository<Order, Long> {

    List<Order> findOrderByUser(User user);
}

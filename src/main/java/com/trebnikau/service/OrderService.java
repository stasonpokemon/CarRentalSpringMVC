package com.trebnikau.service;

import com.trebnikau.entity.Car;
import com.trebnikau.entity.Order;
import com.trebnikau.entity.User;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface OrderService {


    Iterable<Order> findAll();

    List<Order> findOrderByUser(User user);

    String createOrder(User userFromAuthentication, Car car, Model model);

    String saveOrder(User user, Car car, Order order, BindingResult bindingResult, Model model);

    String showAllOrders(Model model);

    String showRentalStatements(Model model);

    String acceptOrCancelOrder(String typeOfRequest, Order order);

    String returnProcessing(Model model);

    String addRefundWithOrWithoutDamage(String typeOfRequest, Order order, String damageDescription, String repairCost);
}

package com.trebnikau.service.impl;

import com.trebnikau.entity.*;
import com.trebnikau.repo.OrderRepo;
import com.trebnikau.repo.UserRepo;
import com.trebnikau.service.OrderService;
import com.trebnikau.threads.MailSenderThread;
import com.trebnikau.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private MailSenderServiceImpl mailSenderService;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public Iterable<Order> findAll(){
        return orderRepo.findAll();
    }

    @Override
    public List<Order> findOrderByUser(User user) {
        return orderRepo.findOrderByUser(user);
    }

    @Override
    public String createOrder(User userFromAuthentication, Car car, Model model) {
        // При первом входе userFromAuthentication не будет иметь данных о паспорте даже если мы его добавим так как userFromAuthentication хранит данные полученные только при авторизаци
        User user = userRepo.findById(userFromAuthentication.getId()).get();
        if (user.getPassport() == null) {
            model.addAttribute("passportIsAvailable", false);
            return "create-order";
        }

        model.addAttribute("passportIsAvailable", true);
        model.addAttribute("car", car);
        return "create-order";
    }

    @Override
    public String saveOrder(User user, Car car, Order order, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = Util.getErrors(bindingResult);
            model.mergeAttributes(errors);
            model.addAttribute("passportIsAvailable", true);
            return "create-order";
        }
        double orderPrice = (car.getPricePerDay() * order.getRentalPeriod());
        car.setEmploymentStatus(false);
        order.setCar(car);
        order.setUser(user);
        order.setOrderDate(new Timestamp(new Date().getTime()));
        order.setPrice(orderPrice);
        order.setOrderStatus(OrderStatus.UNDER_CONSIDERATION);
        orderRepo.save(order);
        model.addAttribute("notification", true);
        return "redirect:/cars";
    }

    @Override
    public String showAllOrders(Model model) {
        Iterable<Order> allOrders = orderRepo.findAll();
        model.addAttribute("orders", allOrders);
        return "show-all-orders";
    }

    @Override
    public String showRentalStatements(Model model) {
        List<Order> orders = orderRepo.findOrderByOrderStatus(OrderStatus.UNDER_CONSIDERATION);
        model.addAttribute("orders", orders);
        return "show-rental-statements";
    }

    @Override
    public String acceptOrCancelOrder(String typeOfRequest, Order order) {
        User user = order.getUser();
        Car car = order.getCar();
        String message = null;
        String subject = null;
        if ("accept".equals(typeOfRequest)) {
            order.setOrderStatus(OrderStatus.CONFIRMED);
            subject = "Order accepted";
            message = String.format("Hello, %s!\nYour order is accepted. You can get a car(%s %s) in the showroom.",
                    user.getUsername(), car.getProducer(), car.getModel());
        } else if ("cancel".equals(typeOfRequest)) {
            order.setOrderStatus(OrderStatus.REFUSAL);
            order.getCar().setEmploymentStatus(true);
            subject = "Order cancelled";
            message = String.format("Hello, %s!\nSadly, but your order has been cancelled.",
                    user.getUsername(), car.getProducer(), car.getModel());
        }
        // Отпраляем письмо о статусе заказа пользователю на почту
//        mailSenderService.send(user.getEmail(), subject, message);
        // ОТправляем сообщение отдельным потоком, чтобы User не ждал загрузку
        new MailSenderThread(mailSenderService, user.getEmail(), subject, message).start();


        orderRepo.save(order);
        return "redirect:/orders/rental-statements";
    }

    @Override
    public String returnProcessing(Model model) {
        List<Order> orders = orderRepo.findOrderByRefundAndOrderStatus(null, OrderStatus.CONFIRMED);
        model.addAttribute("orders", orders);
        return "show-return-processing";
    }

    @Override
    public String addRefundWithOrWithoutDamage(String typeOfRequest, Order order, String damageDescription, String repairCost) {
        Refund refund = new Refund();
        refund.setOrder(order);
        refund.setRefundDate(new Timestamp(new Date().getTime()));
        order.setRefund(refund);
        if ("with".equals(typeOfRequest)) {
            refund.setDamageStatus(false);
            refund.setDamageDescription(damageDescription);
            refund.setPrice(Double.parseDouble(repairCost));
            order.getCar().setDamageStatus(damageDescription);
        } else if ("without".equals(typeOfRequest)) {
            refund.setDamageStatus(true);
            refund.setDamageDescription("-");
            refund.setPrice(0);
            order.getCar().setEmploymentStatus(true);
        }
        orderRepo.save(order);
        return "redirect:/orders/return-processing";
    }
}

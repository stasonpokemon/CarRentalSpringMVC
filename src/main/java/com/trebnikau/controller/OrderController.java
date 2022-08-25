package com.trebnikau.controller;

import com.trebnikau.entity.Car;
import com.trebnikau.entity.Order;
import com.trebnikau.entity.User;
import com.trebnikau.repo.OrderRepo;
import com.trebnikau.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderRepo orderRepo;

    @GetMapping("/{user}/{car}")
    public String createOrder(@PathVariable("user") User user,
                              @PathVariable("car") Car car,
                              Model model) {
        if (user.getPassport() == null) {
            model.addAttribute("passportIsAvailable", false);
            return "create-order";
        }

        model.addAttribute("passportIsAvailable", true);
        model.addAttribute("car", car);
        return "create-order";
    }

    @PostMapping("/{user}/{car}")
    public String addOrder(@PathVariable("user") User user,
                           @PathVariable("car") Car car,
                           @RequestParam("rentalPeriod") String rentalPeriod,
                           Model model) {
        double orderPrice = (car.getPricePerDay() * Double.parseDouble(rentalPeriod));
        car.setEmploymentStatus(false);
        Order order = new Order();
        order.setCar(car);
        order.setUser(user);
        order.setOrderDate(new Timestamp(new Date().getTime()));
        order.setPrice(orderPrice);
        order.setRentalPeriod(Integer.parseInt(rentalPeriod));
        order.setOrderStatus("НА РАССМОТРЕНИИ");
        orderRepo.save(order);
        model.addAttribute("notification",true);
        return "redirect:/cars";
    }

    @GetMapping("/list")
    public  String showAllOrders(Model model){
        Iterable<Order> allOrders = orderRepo.findAll();
        model.addAttribute("orders",allOrders);
        return "show-all-orders";
    }
}

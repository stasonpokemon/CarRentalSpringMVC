package com.trebnikau.controller;

import com.trebnikau.entity.*;
import com.trebnikau.repo.OrderRepo;
import com.trebnikau.repo.RefundRepo;
import com.trebnikau.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private RefundRepo refundRepo;

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
        order.setOrderStatus(OrderStatus.UNDER_CONSIDERATION);
        orderRepo.save(order);
        model.addAttribute("notification", true);
        return "redirect:/cars";
    }

    @GetMapping("/list")
    public String showAllOrders(Model model) {
        Iterable<Order> allOrders = orderRepo.findAll();
        model.addAttribute("orders", allOrders);
        return "show-all-orders";
    }

    @GetMapping("/rental-applications")
    public String showRentalApplications(Model model) {
        List<Order> orders = orderRepo.findOrderByOrderStatus(OrderStatus.UNDER_CONSIDERATION);
        model.addAttribute("orders", orders);
        return "show-rental-applications";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/rental-applications/accept")
    public String acceptOrder(@RequestParam("orderId") Order order) {
        order.setOrderStatus(OrderStatus.CONFIRMED);
        orderRepo.save(order);
        return "redirect:/orders/rental-applications";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/rental-applications/cancel")
    public String cancelOrder(@RequestParam("orderId") Order order) {
        order.setOrderStatus(OrderStatus.REFUSAL);
        order.getCar().setEmploymentStatus(true);
        orderRepo.save(order);
        return "redirect:/orders/rental-applications";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/return-processing")
    public String returnProcessing(Model model) {
        List<Order> orders = orderRepo.findOrderByRefundAndOrderStatus(null, OrderStatus.CONFIRMED);
        model.addAttribute("orders", orders);
        return "return-processing";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/return-processing/without")
    public String addRefundWithoutDamage(@RequestParam("orderId") Order order) {
        Refund refund = new Refund();
        refund.setOrder(order);
        refund.setDamageStatus(true);
        refund.setDamageDescription("-");
        refund.setPrice(0);
        refund.setRefundDate(new Timestamp(new Date().getTime()));
        order.setRefund(refund);
        order.getCar().setEmploymentStatus(true);
        orderRepo.save(order);
        return "redirect:/orders/return-processing";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/return-processing/with")
    public String addRefundWithDamage(@RequestParam("orderId") Order order,
                                      @RequestParam("damageDescription") String damageDescription,
                                      @RequestParam("repairCost") String repairCost) {
        Refund refund = new Refund();
        refund.setOrder(order);
        refund.setDamageStatus(false);
        refund.setDamageDescription(damageDescription);
        refund.setPrice(Double.parseDouble(repairCost));
        refund.setRefundDate(new Timestamp(new Date().getTime()));
        order.setRefund(refund);
        orderRepo.save(order);
        return "redirect:/orders/return-processing";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/refunds/list")
    public String showAllRefunds(Model model) {
        Iterable<Refund> refunds = refundRepo.findAll();
        model.addAttribute("refunds",refunds);
        return "show-all-refunds";
    }

}
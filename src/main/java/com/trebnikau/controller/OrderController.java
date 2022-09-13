package com.trebnikau.controller;

import com.trebnikau.entity.*;
import com.trebnikau.service.OrderService;
import com.trebnikau.service.RefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private RefundService refundService;

    @Autowired
    private OrderService orderService;

    @GetMapping()
    public String showAllOrders(Model model) {
        return orderService.showAllOrders(model);
    }

    @GetMapping("/create/{user}/{car}")
    public String createOrder(@PathVariable("user") User user,
                              @PathVariable("car") Car car,
                              Model model) {
        return orderService.createOrder(user, car, model);
    }

    @PostMapping("/create/{user}/{car}")
    public String saveOrder(@PathVariable("user") User user,
                            @PathVariable("car") Car car,
                            @Valid Order order,
                            BindingResult bindingResult,
                            Model model) {
        return orderService.saveOrder(user, car, order, bindingResult, model);
    }


    @GetMapping("/rental-statements")
    public String showRentalStatements(Model model) {
        return orderService.showRentalStatements(model);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/rental-statements/{type}")
    public String acceptOrCancelOrder(@PathVariable("type") String typeOfRequest,
                                      @RequestParam("orderId") Order order) {
        return orderService.acceptOrCancelOrder(typeOfRequest, order);
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/return-processing")
    public String returnProcessing(Model model) {
        return orderService.returnProcessing(model);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/return-processing/{type}")
    public String addRefundWithOrWithoutDamage(@PathVariable("type") String typeOfRequest,
                                               @RequestParam("orderId") Order order,
                                               @RequestParam(value = "damageDescription", required = false, defaultValue = "") String damageDescription,
                                               @RequestParam(value = "repairCost", required = false, defaultValue = "") String repairCost) {
        return orderService.addRefundWithOrWithoutDamage(typeOfRequest, order, damageDescription, repairCost);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/refunds/list")
    public String showAllRefunds(Model model) {
        return  refundService.showAllRefunds(model);
    }

}
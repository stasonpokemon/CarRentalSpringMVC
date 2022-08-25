package com.trebnikau.controller;

import com.trebnikau.entity.ClientPassport;
import com.trebnikau.entity.Order;
import com.trebnikau.entity.Role;
import com.trebnikau.entity.User;
import com.trebnikau.repo.OrderRepo;
import com.trebnikau.repo.UserRepo;
import com.trebnikau.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String userList(@RequestParam(required = false, name = "filter", defaultValue = "") String filter,
                           Model model) {
        Iterable<User> users;
        if (filter != null && !filter.isEmpty()) {
            users = userRepo.findAllByUsername(filter);
        } else {
            users = userRepo.findAll();
        }
        model.addAttribute("users", users);
        return "user-list";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}")
    public String userEdit(@PathVariable("id") User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "user-edit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping()
    public String saveUser(@RequestParam("userId") User user,
                           @RequestParam("userName") String userName,
                           @RequestParam Map<String, String> form) {
        userService.saveUserAfterEditing(user, userName, form);
        return "redirect:/user";
    }

    @GetMapping("/{user}/passport")
    public String showPassport(@PathVariable("user") User user,
                               Model model){
        if (user.getPassport() != null){
            ClientPassport passport = user.getPassport();
            model.addAttribute("passportIsAvailable", true);
            model.addAttribute("passport", passport);
        } else {
            model.addAttribute("passportIsAvailable", false);
        }
        return "show-passport";
    }

    @GetMapping("/{user}/passport/edit")
    public String editPassport(@PathVariable("user") User user,
                               Model model){
        ClientPassport passport = user.getPassport();
        model.addAttribute("passport",passport);
        return "edit-passport";
    }

    @PostMapping("/{user}/passport/edit")
    public String saveEditPassport(@PathVariable("user") User user,
                               @RequestParam("name") String name,
                               @RequestParam("surname") String surname,
                               @RequestParam("patronymic") String patronymic,
                               @RequestParam("birthday") String birthday,
                               @RequestParam("address") String address
    ){
        if(user != null && user.getPassport() != null){
            ClientPassport passport = user.getPassport();
            passport.setName(name);
            passport.setSurname(surname);
            passport.setPatronymic(patronymic);
            passport.setBirthday(birthday);
            passport.setAddress(address);
            user.setPassport(passport);
            userService.save(user);
        }
        return "redirect:/user/" + user.getId() + "/passport";
    }

    @GetMapping("/{user}/passport/add")
    public String addPassport(@PathVariable("user") User user){
        return "create-passport";
    }

    @PostMapping("/{user}/passport/add")
    public String savePassport(@PathVariable("user") User user,
                               @RequestParam("name") String name,
                               @RequestParam("surname") String surname,
                               @RequestParam("patronymic") String patronymic,
                               @RequestParam("birthday") String birthday,
                               @RequestParam("address") String address
    ){
        if(user != null && user.getPassport() == null){
            ClientPassport passport = new ClientPassport();
            passport.setName(name);
            passport.setSurname(surname);
            passport.setPatronymic(patronymic);
            passport.setBirthday(birthday);
            passport.setAddress(address);
            user.setPassport(passport);
            userService.save(user);
        }
        return "redirect:/cars";
    }

    @GetMapping("/{user}/orders/list")
    public String showUserOrders(@PathVariable("user") User user,
                            Model model){
        List<Order> orderByUser = orderRepo.findOrderByUser(user);
        model.addAttribute("orders", orderByUser);
        return "show-users-orders";
    }



}

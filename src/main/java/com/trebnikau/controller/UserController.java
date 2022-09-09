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
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public String saveUser(@RequestParam("id") User user,
                           @RequestParam("username") String userName,
                           @RequestParam Map<String, String> form,
                           Model model) {
        // Проверяем, пустое ли имя пользователя
        boolean isUsernameEmpty = StringUtils.isEmpty(userName);
        // Проверяем, содержит ли форма роли. Если содержит, то сохранаяем пользователя, если нет, то кидаем валидацию на форму
        if (userService.isEditUserFormContainsRoles(form) && !isUsernameEmpty) {
            userService.saveUserAfterEditing(user, userName, form);
        }else {
            // Если мы поменяли имя в форме, но вылезла валидация ролей, то кидаем в форму изменённое имя
            model.addAttribute("username", userName);
            if (isUsernameEmpty){
                model.addAttribute("usernameError", "Username can't be empty");
            }
            System.out.println("not contains");
            model.addAttribute("user", user);
            model.addAttribute("roles", Role.values());
            model.addAttribute("rolesError", "Roles can't be empty .Select one or more roles for the user");
            return "user-edit";
        }
        return "redirect:/user";
    }


    @GetMapping("/{user}/passport")
    public String showPassport(@PathVariable("user") User user,
                               Model model) {
        if (user.getPassport() != null) {
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
                               Model model) {
        ClientPassport passport = user.getPassport();
        model.addAttribute("passport", passport);
        return "edit-passport";
    }

    @GetMapping("/{user}/passport/add")
    public String addPassport(@PathVariable("user") User user,
                              Model model) {
        model.addAttribute("user", user);
        return "create-passport";
    }

    @PostMapping("/{user}/passport/{type}")
    public String savePassport(@PathVariable("user") User user,
                               @PathVariable("type") String type,
                               @Valid ClientPassport clientPassport,
                               BindingResult bindingResult,
                               Model model) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = UtilsController.getErrors(bindingResult);
            model.mergeAttributes(errors);
            model.addAttribute("user", user);
            model.addAttribute("passport", clientPassport);
            if ("add".equals(type)) {
                return "create-passport";
            } else {
                return "edit-passport";
            }
        }

        if ("add".equals(type) && user != null && user.getPassport() == null) {
            user.setPassport(clientPassport);
            userService.save(user);
            return "redirect:/cars";
        } else if ("edit".equals(type) && user != null && user.getPassport() != null) {
            user.setPassport(clientPassport);
            userService.save(user);
            return "redirect:/user/" + user.getId() + "/passport";
        } else {
            return "redirect:/cars";
        }
    }

    @GetMapping("/{user}/orders/list")
    public String showUserOrders(@PathVariable("user") User user,
                                 Model model) {
        List<Order> orderByUser = orderRepo.findOrderByUser(user);
        model.addAttribute("orders", orderByUser);
        return "show-users-orders";
    }


}

package com.trebnikau.controller;

import com.trebnikau.entity.ClientPassport;
import com.trebnikau.entity.Role;
import com.trebnikau.entity.User;
import com.trebnikau.service.impl.OrderServiceImpl;
import com.trebnikau.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    private UserServiceImpl userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String showUserList(@RequestParam(required = false, name = "filter", defaultValue = "") String filter,
                               Model model) {
        return userService.showUserList(filter, model);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}")
    public String userEdit(@PathVariable("id") User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "edit-user";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping()
    public String saveUser(@RequestParam("id") User user,
                           @RequestParam("username") String username,
                           @RequestParam Map<String, String> form,
                           Model model) {
        return userService.saveUserAfterChangingItByAdmin(user, username, form, model);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/{user}/{type}")
    public String blockOrUnlockUser(@PathVariable("user") User user,
                                    @PathVariable("type") String typeOfRequest){
        return userService.blockOrUnlockUser(user, typeOfRequest);
    }

    @GetMapping("/{user}/profile")
    public String showUserProfile(@PathVariable("user") User user,
                                  Model model) {
        model.addAttribute("user", user);
        return "show-user-profile";
    }

    @GetMapping("/{user}/profile/edit_password")
    public String editUserPassword(@PathVariable("user") User user,
                                   Model model) {
        model.addAttribute("user", user);
        return "edit-user-password";
    }

    @PostMapping("/{user}/profile/edit_password")
    public String saveUserPassword(@PathVariable("user") User user,
                                   @RequestParam("currentPassword") String currentPassword,
                                   @RequestParam("newPassword") String newPassword,
                                   @RequestParam("passwordConfirmation") String passwordConfirmation,
                                   Model model) {
        return userService.saveUserPassword(user, currentPassword, newPassword, passwordConfirmation, model);
    }

    @GetMapping("/{user}/profile/edit_username")
    public String editUserUsername(@PathVariable("user") User user,
                                   Model model) {
        model.addAttribute("user", user);
        return "edit-user-username";
    }


    // Изменённый username менятся в бд, но в профиле поменяется только после повторной авторизации
    @PostMapping("/{user}/profile/edit_username")
    public String saveUserUsername(@PathVariable("user") User user,
                                   @RequestParam("username") String newUsername,
                                   Model model) {
        return userService.saveUserUsername(user, newUsername, model);
    }


    @GetMapping("/{user}/passport")
    public String showUserPassport(@PathVariable("user") User user,
                                   Model model) {
        return userService.showUserPassport(user, model);
    }

    @GetMapping("/{user}/passport/add")
    public String addPassport(@PathVariable("user") User user,
                              Model model) {
        model.addAttribute("user", user);
        return "create-passport";
    }

    @GetMapping("/{user}/passport/edit")
    public String editPassport(@PathVariable("user") User user,
                               Model model) {
        model.addAttribute("passport", user.getPassport());
        return "edit-user-passport";
    }


    @PostMapping("/{user}/passport/{type}")
    public String savePassport(@PathVariable("user") User user,
                               @PathVariable("type") String type,
                               @Valid ClientPassport clientPassport,
                               BindingResult bindingResult,
                               Model model) {
        return userService.saveUserPassport(user, type, clientPassport, bindingResult, model);
    }

    @GetMapping("/{user}/orders/list")
    public String showUserOrders(@PathVariable("user") User user,
                                 Model model) {
        model.addAttribute("orders", orderService.findOrderByUser(user));
        return "show-users-orders";
    }


}

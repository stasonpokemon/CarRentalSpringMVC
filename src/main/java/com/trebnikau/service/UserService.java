package com.trebnikau.service;

import com.trebnikau.entity.ClientPassport;
import com.trebnikau.entity.Role;
import com.trebnikau.entity.User;
import com.trebnikau.repo.UserRepo;
import com.trebnikau.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class UserService implements UserDetailsService {


    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findUserByUsername(username);
    }


    public void saveUserAfterEditing(User user, String userName, Map<String, String> form) {
        user.setUsername(userName);
        addUserRoles(user, form);
        // если в чекбоксе небыло выбранно ни одной роли, тогда автоматически присваетвается роль user
        if (user.getRoles().size() == 0) {
            user.getRoles().add(Role.USER);
        }
        save(user);
    }

    public void addUserRoles(User user, Map<String, String> form) {
        Set<String> roles = getSetOfRoles();
        // для начала нужно очистить все роли у user
        user.getRoles().clear();
        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }
    }

    public Set<String> getSetOfRoles() {
        return Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
    }

    public boolean isEditUserFormContainsRoles(Map<String, String> form) {
        for (String key : form.keySet()) {
            if (getSetOfRoles().contains(key)) {
                return true;
            }
        }
        return false;
    }

    public int addNewUser(User user) {
        User userByUsername = userRepo.findUserByUsername(user.getUsername());
        User userByEmail = userRepo.findUserByEmail(user.getEmail());
        if (userByUsername != null) {
            return -1;
        } else if (userByEmail != null) {
            return 0;
        } else {
            user.setActive(true);
            user.setRoles(Collections.singleton(Role.USER));
            save(user);
            return 1;
        }

    }

    public void save(User user) {
        userRepo.save(user);
    }


    public String saveUserPassport(User user, String type, ClientPassport clientPassport, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = Util.getErrors(bindingResult);
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
            save(user);
            return "redirect:/cars";
        } else if ("edit".equals(type) && user != null && user.getPassport() != null) {
            user.setPassport(clientPassport);
            save(user);
            return "redirect:/user/" + user.getId() + "/passport";
        } else {
            return "redirect:/cars";
        }
    }

    public String saveUserUsername(User user, String newUsername, Model model) {
        if (newUsername == null || newUsername.isEmpty()) {
            model.addAttribute("usernameError", "Please fill the username");
            model.addAttribute("user", user);
            System.out.println(1);
            return "edit-user-username";
        }
        user.setUsername(newUsername);
        save(user);
        return "redirect:/user/" + user.getId() + "/profile";
    }

    public String saveUserPassword(User user, String currentPassword, String newPassword, String passwordConfirmation, Model model) {
        boolean isValidForChange = true;
        if (currentPassword.isEmpty() || currentPassword == null) {
            model.addAttribute("currentPasswordError", "Enter your current password");
            isValidForChange = false;
        } else if (!user.getPassword().equals(currentPassword)) {
            model.addAttribute("currentPasswordError", "Invalid password");
            isValidForChange = false;
        }
        if (newPassword == null || newPassword.isEmpty() || passwordConfirmation == null || passwordConfirmation.isEmpty()) {
            model.addAttribute("passwordError", "Password fields must be filled");
            isValidForChange = false;
        } else if (!newPassword.equals(passwordConfirmation)) {
            model.addAttribute("passwordError", "Passwords must be the same");
            isValidForChange = false;
        }
        if (!isValidForChange) {
            return "edit-user-password";
        } else {
            user.setPassword(newPassword);
            save(user);
            return "redirect:/user/" + user.getId() + "/profile";
        }
    }

    public String saveUserAfterChangingItByAdmin(User user, String username, Map<String, String> form, Model model) {
        // Проверяем, пустое ли имя пользователя
        boolean isUsernameEmpty = StringUtils.isEmpty(username);
        // Проверяем, содержит ли форма роли и является ли username заполненным. Если всё верно, то сохраняем пользователя, если нет, то кидаем валидацию на форму
        if (isEditUserFormContainsRoles(form) && !isUsernameEmpty) {
            saveUserAfterEditing(user, username, form);
        } else {
            if (isUsernameEmpty) {
                model.addAttribute("usernameError", "Username can't be empty");
            }
            // Если мы поменяли имя в форме, но вылезла валидация ролей, то кидаем в форму изменённое имя
            model.addAttribute("username", username);
            model.addAttribute("user", user);
            model.addAttribute("roles", Role.values());
            model.addAttribute("rolesError", "Roles can't be empty .Select one or more roles for the user");
            return "edit-user";
        }
        return "redirect:/user";
    }

    public String showUserList(String filter, Model model) {
        Iterable<User> users;
        if (filter != null && !filter.isEmpty()) {
            users = userRepo.findAllByUsername(filter);
        } else {
            users = userRepo.findAll();
        }
        model.addAttribute("users", users);
        return "show-all-users";
    }

    public String showUserPassport(User user, Model model) {
        if (user.getPassport() != null) {
            ClientPassport passport = user.getPassport();
            model.addAttribute("passportIsAvailable", true);
            model.addAttribute("passport", passport);
        } else {
            model.addAttribute("passportIsAvailable", false);
        }
        return "show-user-passport";
    }

    public String addRegisteredUser(String passwordConfirmation, User user, BindingResult bindingResult, Model model) {

        boolean isPasswordConfirmationEmpty = false;
        if (StringUtils.isEmpty(passwordConfirmation)) {
            isPasswordConfirmationEmpty = true;
            model.addAttribute("passwordConfirmationError", "Password confirmation can't be empty");
        }

        boolean isDifferentPasswords = false;
        if (user.getPassword() != null && !passwordConfirmation.equals(user.getPassword())) {
            model.addAttribute("passwordError", "Passwords are different");
            isDifferentPasswords = true;
        }

        if (bindingResult.hasErrors() || isPasswordConfirmationEmpty) {
            Map<String, String> errors = Util.getErrors(bindingResult);
            model.mergeAttributes(errors);
            model.addAttribute("user", user);
            return "registration";
        } else {
            if (isDifferentPasswords) {
                model.addAttribute("user", user);
                return "registration";
            } else {
                if (addNewUser(user) == -1) {
                    model.addAttribute("usernameError", "User with entered username exist");
                    model.addAttribute("user", user);
                    return "registration";
                } else if (addNewUser(user) == 0) {
                    model.addAttribute("emailError", "Entered email is busy");
                    model.addAttribute("user", user);
                    return "registration";
                } else {
                    return "redirect:/login";
                }
            }
        }
    }

    public String blockOrUnlockUser(User user, String typeOfRequest) {
        if ("block".equals(typeOfRequest)) {
            user.setActive(false);
        } else if ("unlock".equals(typeOfRequest)){
            user.setActive(true);
        }
        return "redirect:/user";
    }
}

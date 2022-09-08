package com.trebnikau.service;

import com.trebnikau.entity.Role;
import com.trebnikau.entity.User;
import com.trebnikau.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        // для начала нужно очистить все роли у user
        user.getRoles().clear();
        for (String key : form.keySet()) {
            if (roles.contains(key)){
                user.getRoles().add(Role.valueOf(key));
            }
        }
        // если в чекбоксе небыло выбранно ни одной роли, тогда автоматически присваетвается роль user
        if (user.getRoles().size() == 0){
            user.getRoles().add(Role.USER);
        }
        userRepo.save(user);
    }

    public int addNewUser(User user){
        User userByUsername = userRepo.findUserByUsername(user.getUsername());
        User userByEmail = userRepo.findUserByEmail(user.getEmail());
        if (userByUsername != null){
            return -1;
        } else if(userByEmail != null){
            return  0;
        } else {
            user.setActive(true);
            user.setRoles(Collections.singleton(Role.USER));
            userRepo.save(user);
            return 1;
        }

    }

    public void save(User user) {
        userRepo.save(user);
    }
}

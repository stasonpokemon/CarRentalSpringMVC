package com.trebnikau.service.impl;

import com.trebnikau.entity.User;
import com.trebnikau.repo.UserRepo;
import com.trebnikau.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userByUsername = userRepo.findUserByUsername(username);

        if (!userByUsername.isPresent()) {
            throw new UsernameNotFoundException(new StringBuilder("User with name = ")
                    .append(username)
                    .append(" not found").toString());
        }
        return new UserDetailsImpl(userByUsername.get());

    }
}

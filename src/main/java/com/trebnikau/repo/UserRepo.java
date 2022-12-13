package com.trebnikau.repo;

import com.trebnikau.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends CrudRepository<User,Long> {

    Optional<User> findUserByUsername(String username);
    Optional<User> findUserByEmail(String email);
    List<User> findAllByUsername(String username);
    User findUserByActivationCode(String activationCode);
}

package com.trebnikau.repo;

import com.trebnikau.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User,Long> {

    User findUserByUsername(String username);
}

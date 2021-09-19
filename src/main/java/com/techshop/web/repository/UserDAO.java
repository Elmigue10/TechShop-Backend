package com.techshop.web.repository;

import com.techshop.web.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDAO extends CrudRepository<User, Integer> {
    User findByUsername(String username);
}
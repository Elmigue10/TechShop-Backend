package com.techshop.web.model.repository;

import com.techshop.web.model.dto.UserDto;
import com.techshop.web.model.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);
    User findByEmail(String email);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.name=:name, u.email=:email, u.password=:password WHERE u.username=:username")
    void updateByUserName(@Param("username") String username, @Param("name") String name,@Param("email") String email,@Param("password") String password);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.password=:password WHERE u.username=:username")
    void updatePasswordByUserName(@Param("username") String username, @Param("password") String password);



}

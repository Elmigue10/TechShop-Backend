package com.techshop.web.model.repository;

import com.techshop.web.model.dto.UserAddressDto;
import com.techshop.web.model.entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserAddressRepository extends JpaRepository<UserAddress, Integer> {
    @Query("SELECT ua FROM UserAddress ua WHERE ua.idUser=:idUser")
    Optional<List<UserAddress>> findUserAddressById(@Param("idUser") Integer idUser);
}
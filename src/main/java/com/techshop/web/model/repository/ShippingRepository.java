package com.techshop.web.model.repository;

import com.techshop.web.model.entity.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ShippingRepository extends JpaRepository<Shipping, Integer> {
    @Query("SELECT s FROM Shipping s WHERE s.idAddress=:idAddress")
    Optional<List<Shipping>> findShippingByIdAddress(@Param("idAddress") Integer idAddress);
}
package com.techshop.web.model.repository;

import com.techshop.web.model.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {

    @Query("select c from ShoppingCart c where c.idUser=:idUsuario")
    Optional<List<ShoppingCart>> getAllByIdUser(@Param("idUsuario") Integer idUser);

}
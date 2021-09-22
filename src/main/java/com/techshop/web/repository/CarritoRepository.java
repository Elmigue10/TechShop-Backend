package com.techshop.web.repository;

import com.techshop.web.model.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CarritoRepository extends JpaRepository<Carrito, Integer> {

    @Query("select c from Carrito c where c.idUsuario=:idUsuario")
    Optional<Carrito> findByIdUsuario(@Param("idUsuario") Integer idUsuario);

}
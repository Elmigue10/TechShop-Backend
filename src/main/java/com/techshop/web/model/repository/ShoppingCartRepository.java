package com.techshop.web.model.repository;

import com.techshop.web.model.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {

    @Query("select c from ShoppingCart c where c.idUser=:idUsuario")
    Optional<List<ShoppingCart>> getAllByIdUser(@Param("idUsuario") Integer idUser);

    @Modifying
    @Transactional
    @Query("delete from ShoppingCart sc where sc.idUser=:idUsuario and sc.idProduct=:idProducto")
    void deleteByIdUser(@Param("idUsuario") Integer idUsuario, @Param("idProducto") Integer idProducto);

    @Modifying
    @Transactional
    @Query("update ShoppingCart sc set sc.quatity=:cantidad where sc.idUser=:idUsuario and sc.idProduct=:idProducto" )
    void updateByIdUser(@Param("idUsuario") Integer idUsuario, @Param("idProducto") Integer idProducto, @Param("cantidad") int cantidad );

    @Modifying
    @Transactional
    @Query("delete from ShoppingCart sc where sc.idUser=:idUsuario")
    void deleteAllByIdUser(@Param("idUsuario") Integer idUsuario);

}
package com.techshop.web.model.repository;

import com.techshop.web.model.entity.DetalleCarrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface DetalleCarritoRepository extends JpaRepository<DetalleCarrito, Integer> {

    @Query(value = "select dc from DetalleCarrito dc where dc.idUsuario=:idUsuario")
    Optional<List<DetalleCarrito>> findByCarrito(@Param("idUsuario") Integer idUsuario);

    @Modifying
    @Transactional
    @Query("delete from DetalleCarrito dc where dc.idUsuario=:idUsuario and dc.idProducto=:idProducto" )
    void deleteByUsuario(@Param("idUsuario") Integer idUsuario, @Param("idProducto") Integer idProducto);

    @Modifying
    @Transactional
    @Query("update DetalleCarrito dc set dc.cantidad=:cantidad where dc.idUsuario=:idUsuario and dc.idProducto=:idProducto" )
    void updateByUsuario(@Param("idUsuario") Integer idUsuario, @Param("idProducto") Integer idProducto, @Param("cantidad") int cantidad );

}

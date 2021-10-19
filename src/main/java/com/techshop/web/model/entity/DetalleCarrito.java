package com.techshop.web.model.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "detalle_carrito")
public class DetalleCarrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_detalle_carrito")
    private Integer id;

    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "id_producto")
    private Integer idProducto;

    @Column(name = "cantidad")
    private int cantidad;

    public DetalleCarrito(Integer idUsuario, Integer idProducto, int cantidad) {
        this.idUsuario=idUsuario;
        this.idProducto=idProducto;
        this.cantidad=cantidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DetalleCarrito that = (DetalleCarrito) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 2109413396;
    }
}

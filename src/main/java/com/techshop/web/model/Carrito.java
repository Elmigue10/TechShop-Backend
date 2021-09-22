package com.techshop.web.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "carrito")
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_carrito")
    private Integer idCarrito;

    @Column(name="id_usuario")
    private int idUsuario;

    @Column(name="fecha")
    private LocalDateTime fecha;

    public Carrito(Integer idUsuario, LocalDateTime fecha) {
        this.idUsuario=idUsuario;
        this.fecha= fecha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Carrito carrito = (Carrito) o;

        return Objects.equals(idCarrito, carrito.idCarrito);
    }

    @Override
    public int hashCode() {
        return 358362476;
    }
}
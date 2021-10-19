package com.techshop.web.model.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "producto")
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Size(min = 4, max = 60, message = "El nombre debe tener minimo 4 caracteres y maximo 60.")
    @Column(name = "nombre")
    private String nombre;

    @Size(min = 10, max = 300, message = "La descripcion debe tener minimo 10 caracteres y maximo 300.")
    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "imagen_url")
    private String imagenUrl;

    @Column(name = "imagen_id")
    private String imagenId;

    @Column(name = "precio")
    private float precio;

    @Column(name = "cantidad")
    private int cantidad;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Producto producto = (Producto) o;

        return Objects.equals(id, producto.id);
    }

    @Override
    public int hashCode() {
        return 1778999073;
    }
}

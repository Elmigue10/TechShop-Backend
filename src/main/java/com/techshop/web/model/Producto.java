package com.techshop.web.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Data
@Table(name = "producto")
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Size(min = 4, max = 60, message = "El nombre debe tener minimo 4 caracteres y maximo 60.")
    @Column(name = "nombre")
    private String nombre;

    @Size(min = 10, max = 300, message = "La descripcion debe tener minimo 10 caracteres y maximo 300.")
    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "imagen")
    private String imagen;

    @Column(name = "precio")
    private float precio;

    @Column(name = "cantidad")
    private int cantidad;
}

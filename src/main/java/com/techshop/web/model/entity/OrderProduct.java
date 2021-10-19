package com.techshop.web.model.entity;

import lombok.*;

import javax.persistence.Embeddable;

@Getter
@Setter
@ToString
@Embeddable
@RequiredArgsConstructor
public class OrderProduct {

    private String nombre;
    private String descripcion;
    private int cantidad;
    private float valor;
}

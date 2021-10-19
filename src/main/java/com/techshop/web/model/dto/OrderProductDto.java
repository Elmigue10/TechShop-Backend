package com.techshop.web.model.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderProductDto {
    private String nombre;
    private String descripcion;
    private int cantidad;
    private float valor;
}

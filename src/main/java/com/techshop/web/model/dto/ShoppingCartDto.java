package com.techshop.web.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Builder
@Getter
public class ShoppingCartDto {

    private Integer id;
    private String nombre;
    private String descripcion;
    private int cantidad;
    private float valor;

}

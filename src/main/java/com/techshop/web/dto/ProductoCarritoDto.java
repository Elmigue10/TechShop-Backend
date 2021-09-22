package com.techshop.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@RequiredArgsConstructor
public class ProductoCarritoDto {

    private String nombre;
    private String descripcion;
    private int cantidad;
    private float valor;

    public ProductoCarritoDto(String nombre, String descripcion, int cantidad, float valor) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.valor = valor;
    }
}


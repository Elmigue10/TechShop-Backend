package com.techshop.web.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@ToString
@Data
@Getter
@Setter
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


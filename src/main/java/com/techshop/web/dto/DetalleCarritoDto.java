package com.techshop.web.dto;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class DetalleCarritoDto {
    private Integer idUsuario;
    private Integer idProducto;
    private int cantidad;
}
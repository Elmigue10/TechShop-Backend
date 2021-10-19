package com.techshop.web.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Data
public class DetalleCarritoDto {
    private Integer idUsuario;
    private Integer idProducto;
    private int cantidad;
}
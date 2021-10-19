package com.techshop.web.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

@ToString
@Data
public class CarritoDto {

    @JsonProperty("idCliente")
    private int idCliente;

    @JsonProperty("productos")
    private ArrayList<ProductoCarritoDto> productos;

    public CarritoDto(int idCliente, ArrayList<ProductoCarritoDto> productos) {
        this.idCliente = idCliente;
        this.productos = productos;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public ArrayList<ProductoCarritoDto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<ProductoCarritoDto> productos) {
        this.productos = productos;
    }

}

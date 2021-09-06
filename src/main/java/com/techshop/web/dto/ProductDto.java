package com.techshop.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@ToString
@Data
public class ProductDto implements Serializable {

    @NotNull(message = "Nombre del producto es requerido")
    @Size(min = 4, max = 60, message = "El nombre debe tener minimo 4 caracteres y maximo 60.")
    @JsonProperty("nombre")
    private String nombre;

    @NotNull(message = "Descripcion del producto requerido")
    @Size(min = 10, max = 300, message = "La descripcion debe tener minimo 10 caracteres y maximo 300.")
    @JsonProperty("descripcion")
    private String descripcion;

    @NotNull(message = "La imagen del prodcuto es requerida")
    @JsonProperty("imagen")
    private String imagen;

    @NotNull(message = "El precio del prodcuto es requerido")
    @JsonProperty("precio")
    private float precio;

    @NotNull(message = "La cantidad del prodcuto es requerida")
    @JsonProperty("cantidad")
    private int cantidad;

}

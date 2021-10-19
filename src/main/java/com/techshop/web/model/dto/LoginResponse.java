package com.techshop.web.model.dto;

import lombok.Data;

@Data
public class LoginResponse {

    private String id;
    private String nombre;
    private String token;

}

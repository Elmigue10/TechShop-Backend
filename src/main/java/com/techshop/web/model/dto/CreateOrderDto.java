package com.techshop.web.model.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateOrderDto {
    private String username;
    private String address;
}

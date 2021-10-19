package com.techshop.web.model.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserAddressDto {
    private Integer idUser;
    private Integer idAddress;
}

package com.techshop.web.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@RequiredArgsConstructor
public class AddressDto {
    private String address;
    private String city ;
    private String region;
    private String phone;
    private Integer idUser;
}

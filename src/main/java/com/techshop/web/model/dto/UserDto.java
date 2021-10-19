package com.techshop.web.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class UserDto {
    private String name;
    private String username;
    private String password;
    private String email;
    private String enable;
}

package com.techshop.web.model.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserNamePassword {
    private String username;
    private String password;
}

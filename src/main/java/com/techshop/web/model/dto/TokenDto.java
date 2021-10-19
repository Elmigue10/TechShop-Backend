package com.techshop.web.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@RequiredArgsConstructor
public class TokenDto {
    private String token;
}
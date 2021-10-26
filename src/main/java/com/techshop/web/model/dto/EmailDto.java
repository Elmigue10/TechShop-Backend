package com.techshop.web.model.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class EmailDto {
    private String from;
    private String to;
    private String subject;
    private String body;
}

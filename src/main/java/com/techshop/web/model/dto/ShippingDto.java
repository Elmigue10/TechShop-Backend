package com.techshop.web.model.dto;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter
@Builder
public class ShippingDto {
    private LocalDateTime sentAt;
    private LocalDateTime received;
    private Integer idAddress;
    private Integer idOrderHistory;
}

package com.techshop.web.model.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
@Builder
@Getter
public class SummaryShipping {
    private LocalDateTime sentAt;
    private String address;
    private Integer idOrder;
    private float totalPrice;
}

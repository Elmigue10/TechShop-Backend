package com.techshop.web.model.dto;

import com.techshop.web.model.entity.OrderProduct;
import com.techshop.web.model.entity.ShoppingCart;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class OrderHistoryDto {
    private Integer id ;
    private Integer quantity ;
    private Integer totalPrice;
    private List<OrderProduct> orderProducts;
}

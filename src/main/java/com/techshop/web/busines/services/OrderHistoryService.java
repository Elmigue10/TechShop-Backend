package com.techshop.web.busines.services;

import com.techshop.web.busines.mapper.OrderHistoryMapper;
import com.techshop.web.busines.mapper.OrderProductMapper;
import com.techshop.web.model.dto.OrderHistoryDto;
import com.techshop.web.model.dto.ShoppingCartDto;
import com.techshop.web.model.entity.OrderProduct;
import com.techshop.web.model.repository.OrderHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderHistoryService {
    @Autowired
    private OrderHistoryRepository orderHistoryRepository;
    @Autowired
    private ShoppingCartService shoppingCartService;

    public void saveOrder(Integer idUser) throws Exception {
        int quantity=0;
        int totalPrice=0;
        List<ShoppingCartDto> shoppingCartDtoList = shoppingCartService.getDetailCartShopping(idUser);
        List<OrderProduct> orderProducts = new ArrayList<>();
        for(ShoppingCartDto shoppingCartDto:shoppingCartDtoList){
            orderProducts.add(OrderProductMapper.ORDER_PRODUCT_MAPPER.toEntity(shoppingCartDto));
        }
        for(OrderProduct orderProduct:orderProducts){
            System.out.println(orderProduct.toString());
        }

        orderHistoryRepository.save(OrderHistoryMapper.ORDER_HISTORY_MAPPER.toEntity(
                OrderHistoryDto.builder()
                .quantity(quantity)
                .totalPrice(totalPrice)
                .orderProducts(orderProducts)
                .build()
        ));




    }
}

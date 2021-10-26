package com.techshop.web.busines.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techshop.web.busines.mapper.OrderHistoryMapper;
import com.techshop.web.busines.mapper.ShippingMapper;
import com.techshop.web.model.dto.CreateOrderDto;
import com.techshop.web.model.dto.OrderHistoryDto;
import com.techshop.web.model.dto.ShippingDto;
import com.techshop.web.model.dto.ShoppingCartDto;
import com.techshop.web.model.entity.*;
import com.techshop.web.model.repository.OrderHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderHistoryService {
    @Autowired
    private OrderHistoryRepository orderHistoryRepository;
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private UserService userService;
    @Autowired
    private ShippingService shippingService;

    public Optional<OrderHistory> findOrderHistory(Integer id){
        return orderHistoryRepository.findById(id);
    }

    public void saveOrder(CreateOrderDto createOrderDto) throws Exception {

        int quantity=0;
        int totalPrice=0;

        User user = userService.findUserByUserName(createOrderDto.getUsername());
        Address address = addressService.findIdByAddress(createOrderDto.getAddress());
        List<ShoppingCartDto> shoppingCartDtoList = shoppingCartService.getDetailCartShopping(user.getId());
        String products = new ObjectMapper().writeValueAsString(shoppingCartDtoList);

        for(ShoppingCartDto shoppingCartDto:shoppingCartDtoList){
            quantity+=shoppingCartDto.getCantidad();
            totalPrice+=shoppingCartDto.getValor();
        }

        OrderHistory orderHistory=OrderHistoryMapper.ORDER_HISTORY_MAPPER.toEntity(
                OrderHistoryDto.builder()
                        .quantity(quantity)
                        .totalPrice(totalPrice)
                        .products(products)
                        .build()
        );
        orderHistoryRepository.save(orderHistory);

        Shipping shipping = ShippingMapper.SHIPPING.toEntity(
                ShippingDto.builder()
                        .sentAt(LocalDateTime.now())
                        .idAddress(address.getId())
                        .idOrderHistory(orderHistory.getId())
                        .build()
        );

        shippingService.save(shipping);
        shoppingCartService.deleteShoppingCart(user.getId());
    }
}

package com.techshop.web.busines.controller;

import com.techshop.web.busines.services.OrderHistoryService;
import com.techshop.web.model.dto.CreateOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("techshop/web/v1/order")
public class OrderHistoryController {
    @Autowired
    private OrderHistoryService orderHistoryService;

    @PostMapping(value = "/save")
    public ResponseEntity saveOrder(@RequestBody CreateOrderDto createOrderDto) throws Exception {
        orderHistoryService.saveOrder(createOrderDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}

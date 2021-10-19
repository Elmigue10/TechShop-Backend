package com.techshop.web.busines.controller;

import com.techshop.web.busines.services.OrderHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("techshop/web/v1/order")
public class OrderHistoryController {
    @Autowired
    private OrderHistoryService orderHistoryService;

    @PostMapping(value = "/save/{id}")
    public ResponseEntity saveOrder(@PathVariable("id") Integer id) throws Exception {
        orderHistoryService.saveOrder(id);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}

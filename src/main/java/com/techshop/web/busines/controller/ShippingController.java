package com.techshop.web.busines.controller;

import com.techshop.web.busines.services.ShippingService;
import com.techshop.web.model.dto.SummaryShipping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Access;
import java.util.List;

@RestController
@RequestMapping("techshop/web/v1/shipping")
public class ShippingController {
    @Autowired
    private ShippingService shippingService;
    @GetMapping("/user/{username}")
    public ResponseEntity<List<SummaryShipping>> getSummaryShipping(@PathVariable("username") String username){
        return new ResponseEntity<>(shippingService.findSummaryShipping(username), HttpStatus.OK);
    }
}

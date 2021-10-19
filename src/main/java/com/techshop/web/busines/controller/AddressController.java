package com.techshop.web.busines.controller;

import com.techshop.web.busines.services.AddressService;
import com.techshop.web.model.dto.AddressDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("techshop/web/v1/address")
public class AddressController {
    @Autowired
    AddressService addressService;

    @GetMapping("/user/{id}")
    public ResponseEntity<List<AddressDto>> getAllAddressByUser(@PathVariable("id") Integer id) throws Exception {
        return new ResponseEntity<>(addressService.getAllAddress(id), HttpStatus.OK);
    }
    @PostMapping("/save")
    public ResponseEntity saveAddress(@RequestBody AddressDto addressDto){
        addressService.saveAddress(addressDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity updateAddress(@RequestBody AddressDto addressDto){
        addressService.updateAddress(addressDto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteAddress(@RequestBody AddressDto addressDto){
        addressService.deleteAddress(addressDto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}

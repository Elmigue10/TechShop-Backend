package com.techshop.web.busines.controller;

import com.techshop.web.busines.services.ShoppingCartService;
import com.techshop.web.model.dto.DetalleCarritoDto;
import com.techshop.web.model.dto.ProductoCarritoDto;
import com.techshop.web.busines.services.CarritoService;
import com.techshop.web.model.dto.ShoppingCartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("techshop/web/v1/carrito")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,
        RequestMethod.PUT, RequestMethod.DELETE})
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping(value = "/producto/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ShoppingCartDto>> getProductosCarrito(@PathVariable("id") Integer id) throws Exception {
        return new ResponseEntity<>(shoppingCartService.getDetailCartShopping(id), HttpStatus.OK);
    }

    @DeleteMapping("/producto")
    ResponseEntity deleteProdut(@RequestBody DetalleCarritoDto detalleCarritoDto){
        shoppingCartService.deleteProduct(detalleCarritoDto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/producto")
    ResponseEntity updateProduct(@RequestBody DetalleCarritoDto detalleCarritoDto){
        shoppingCartService.updateProduct(detalleCarritoDto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/producto")
    ResponseEntity saveProduct(@RequestBody DetalleCarritoDto detalleCarritoDto){
        shoppingCartService.saveProduct(detalleCarritoDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}

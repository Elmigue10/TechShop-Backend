package com.techshop.web.controller;

import com.techshop.web.dto.DetalleCarritoDto;
import com.techshop.web.dto.ProductoCarritoDto;
import com.techshop.web.services.CarritoService;
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

    @GetMapping(value = "/producto/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ProductoCarritoDto>> getProductosCarrito(@PathVariable("id") Integer id) throws Exception {
        return new ResponseEntity<>(carritoService.obtenerDetalle(id), HttpStatus.OK);
    }

    @DeleteMapping("/producto")
    ResponseEntity deleteProduto(@RequestBody DetalleCarritoDto detalleCarritoDto){
        carritoService.eliminarProducto(detalleCarritoDto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/producto")
    ResponseEntity updateProducto(@RequestBody DetalleCarritoDto detalleCarritoDto){
        carritoService.actualizarProducto(detalleCarritoDto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/producto")
    ResponseEntity createProducto(@RequestBody DetalleCarritoDto detalleCarritoDto){
        carritoService.agregarProducto(detalleCarritoDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}

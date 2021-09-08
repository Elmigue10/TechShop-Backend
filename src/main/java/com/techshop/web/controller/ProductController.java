package com.techshop.web.controller;

import com.techshop.web.dto.ProductDto;
import com.techshop.web.model.Producto;
import com.techshop.web.services.interfaces.ProductServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("techshop/web/v1")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,
        RequestMethod.PUT, RequestMethod.DELETE})
public class ProductController {

    @Autowired
    private ProductServiceI productService;

    @PostMapping(value = "/product/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> save(@RequestBody ProductDto request){
        productService.save(request);

        return ResponseEntity.ok(Boolean.TRUE);
    }

    @GetMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findAll(){
        return ResponseEntity.ok(productService.findAll());
    }

    @PutMapping(value = "/product/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateProduct(@PathVariable("id") int id, @RequestBody ProductDto request){
        productService.update(request, id);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @DeleteMapping(value = "/product/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable("id") int id){
        productService.deletedById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }
}

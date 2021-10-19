package com.techshop.web.busines.services.interfaces;

import com.techshop.web.model.dto.ProductDto;
import com.techshop.web.model.entity.Producto;

import java.util.List;

public interface ProductServiceI {

    void save(ProductDto request);
    List<Producto> findAll();
    void update(ProductDto request, int id);
    void deletedById(int id);
    ProductDto findById(int id);
    
}

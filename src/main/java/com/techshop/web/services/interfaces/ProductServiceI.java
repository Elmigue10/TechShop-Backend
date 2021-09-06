package com.techshop.web.services.interfaces;

import com.techshop.web.dto.ProductDto;
import com.techshop.web.model.Producto;

import java.util.List;

public interface ProductServiceI {

    void save(ProductDto request);
    List<Producto> findAll();
    void update(ProductDto request, int id);
    void deletedById(int id);

}

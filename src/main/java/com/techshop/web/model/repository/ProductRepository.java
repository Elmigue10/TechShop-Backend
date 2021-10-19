package com.techshop.web.model.repository;

import com.techshop.web.model.entity.Producto;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Producto, Integer> {

}

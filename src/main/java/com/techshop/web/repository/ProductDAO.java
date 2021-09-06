package com.techshop.web.repository;

import com.techshop.web.model.Producto;
import org.springframework.data.repository.CrudRepository;

public interface ProductDAO extends CrudRepository<Producto, Integer> {

}

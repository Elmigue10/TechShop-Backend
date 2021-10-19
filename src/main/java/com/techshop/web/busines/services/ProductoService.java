package com.techshop.web.busines.services;

import com.techshop.web.model.dto.ProductDto;
import com.techshop.web.model.entity.Producto;
import com.techshop.web.model.repository.ProductRepository;
import com.techshop.web.busines.services.interfaces.ProductServiceI;
import com.techshop.web.busines.mapper.MHelpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService implements ProductServiceI {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void save(ProductDto request) {

        Producto producto = new Producto();

        producto.setNombre(request.getNombre());
        producto.setDescripcion(request.getDescripcion());
        producto.setImagenUrl(request.getImagenUrl());
        producto.setImagenId(request.getImagenId());
        producto.setPrecio(request.getPrecio());
        producto.setCantidad(request.getCantidad());

        productRepository.save(producto);
    }

    @Override
    public List<Producto> findAll() {
        List<Producto> productos = (List<Producto>) productRepository.findAll();
        return productos;
    }

    @Override
    public void update(ProductDto request, int id) {
        Optional<Producto> productoEncontrado = productRepository.findById(id);
        Producto producto = productoEncontrado.get();

        producto.setNombre(request.getNombre());
        producto.setDescripcion(request.getDescripcion());
        producto.setImagenUrl(request.getImagenUrl());
        producto.setImagenId(request.getImagenId());
        producto.setPrecio(request.getPrecio());
        producto.setCantidad(request.getCantidad());

        productRepository.save(producto);
    }

    @Override
    public void deletedById(int id) {
        productRepository.deleteById(id);
    }

    @Override
    public ProductDto findById(int id) {

        Optional<Producto> productoEncontrado = productRepository.findById(id);

        ProductDto producto = MHelpers.modelMapper().map(productoEncontrado.get(), ProductDto.class);

        return producto;
    }
}

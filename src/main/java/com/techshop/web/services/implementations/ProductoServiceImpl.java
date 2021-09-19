package com.techshop.web.services.implementations;

import com.techshop.web.dto.ProductDto;
import com.techshop.web.model.Producto;
import com.techshop.web.repository.ProductDAO;
import com.techshop.web.services.interfaces.ProductServiceI;
import com.techshop.web.utils.helpers.MHelpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductServiceI {

    @Autowired
    private ProductDAO productDAO;

    @Override
    public void save(ProductDto request) {

        Producto producto = MHelpers.modelMapper().map(request, Producto.class);

        productDAO.save(producto);
    }

    @Override
    public List<Producto> findAll() {
        List<Producto> productos = (List<Producto>) productDAO.findAll();
        return productos;
    }

    @Override
    public void update(ProductDto request, int id) {
        Optional<Producto> productoEncontrado = productDAO.findById(id);
        Producto producto = productoEncontrado.get();

        producto.setNombre(request.getNombre());
        producto.setDescripcion(request.getDescripcion());
        producto.setImagenUrl(request.getImagenUrl());
        producto.setImagenId(request.getImagenId());
        producto.setPrecio(request.getPrecio());
        producto.setCantidad(request.getCantidad());

        productDAO.save(producto);
    }

    @Override
    public void deletedById(int id) {
        productDAO.deleteById(id);
    }

    @Override
    public ProductDto findById(int id) {

        Optional<Producto> productoEncontrado = productDAO.findById(id);

        ProductDto producto = MHelpers.modelMapper().map(productoEncontrado.get(), ProductDto.class);

        return producto;
    }
}

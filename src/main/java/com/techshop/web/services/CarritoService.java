package com.techshop.web.services;

import com.techshop.web.dto.DetalleCarritoDto;
import com.techshop.web.dto.ProductoCarritoDto;
import com.techshop.web.model.Carrito;
import com.techshop.web.model.DetalleCarrito;
import com.techshop.web.model.Producto;
import com.techshop.web.model.User;
import com.techshop.web.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CarritoService {
    @Autowired
    private CarritoRepository carritoRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DetalleCarritoRepository detalleCarritoRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<ProductoCarritoDto> obtenerDetalle(Integer id) throws Exception {
        List<ProductoCarritoDto> productos = new ArrayList<>();
        Optional<User> usuario = userRepository.findById(id);
        if(!usuario.isPresent()){
            throw new Exception("El usuario no encontrado");
        }
        Optional<Carrito> carrito = carritoRepository.findByIdUsuario(usuario.get().getId());
        Optional<List<DetalleCarrito>> detalle = detalleCarritoRepository.findByCarrito(carrito.get().getIdUsuario());
        for (DetalleCarrito detalleCarrito: detalle.get()) {
            Optional<Producto> producto= productRepository.findById(detalleCarrito.getIdProducto());
            ProductoCarritoDto productoCarritoDto = new ProductoCarritoDto(producto.get().getNombre(), producto.get().getDescripcion(), detalleCarrito.getCantidad(), (float) producto.get().getPrecio()*detalleCarrito.getCantidad());
            productos.add(productoCarritoDto);
        }
        return productos;
    }

    public void agregarProducto(DetalleCarritoDto detalleCarritoDto){
        Optional<Carrito> carrito = carritoRepository.findByIdUsuario(detalleCarritoDto.getIdUsuario());
        if(!carrito.isPresent()){
            carritoRepository.save(new Carrito(detalleCarritoDto.getIdUsuario(),LocalDateTime.now()));
        }
        detalleCarritoRepository.save(new DetalleCarrito(detalleCarritoDto.getIdUsuario(),detalleCarritoDto.getIdProducto(),detalleCarritoDto.getCantidad()));
    }

    public void eliminarProducto(DetalleCarritoDto detalleCarritoDto){
        detalleCarritoRepository.deleteByUsuario(detalleCarritoDto.getIdUsuario(),detalleCarritoDto.getIdProducto());
    }

    public void actualizarProducto(DetalleCarritoDto detalleCarritoDto){
        detalleCarritoRepository.updateByUsuario(detalleCarritoDto.getIdUsuario(),detalleCarritoDto.getIdProducto(),detalleCarritoDto.getCantidad());
    }
}

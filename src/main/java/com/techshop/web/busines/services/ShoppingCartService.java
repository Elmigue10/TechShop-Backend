package com.techshop.web.busines.services;

import com.techshop.web.model.dto.ShoppingCartDto;
import com.techshop.web.model.entity.Producto;
import com.techshop.web.model.entity.ShoppingCart;
import com.techshop.web.model.entity.User;
import com.techshop.web.model.repository.DetalleCarritoRepository;
import com.techshop.web.model.repository.ProductRepository;
import com.techshop.web.model.repository.ShoppingCartRepository;
import com.techshop.web.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DetalleCarritoRepository detalleCarritoRepository;
    @Autowired
    private ProductRepository productRepository;

    public List<ShoppingCartDto> getDetailCartShopping(Integer id) throws Exception {
        List<ShoppingCartDto> shoppingCartList = new ArrayList<>();
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            throw new Exception("Usuario no encontrado");
        }
        Optional<List<ShoppingCart>> shoppingCarts = shoppingCartRepository.getAllByIdUser(user.get().getId());
        for(ShoppingCart shoppingCart:shoppingCarts.get()){
            Optional<Producto> product= productRepository.findById(shoppingCart.getIdProduct());
            shoppingCartList.add(
                    ShoppingCartDto.builder()
                            .nombre(product.get().getNombre())
                            .descripcion(product.get().getDescripcion())
                            .cantidad(shoppingCart.getQuatity())
                            .valor(shoppingCart.getTotalPrice())
                            .build());
        }
        return shoppingCartList;
    }

}

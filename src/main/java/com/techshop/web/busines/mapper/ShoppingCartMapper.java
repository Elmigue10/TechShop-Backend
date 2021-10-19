package com.techshop.web.busines.mapper;

import com.techshop.web.model.dto.ShoppingCartDto;
import com.techshop.web.model.entity.ShoppingCart;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ShoppingCartMapper extends GenericMapper<ShoppingCart, ShoppingCartDto>{
    ShoppingCartMapper SHOPPING_CART_MAPPER= Mappers.getMapper(ShoppingCartMapper.class);
}

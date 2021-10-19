package com.techshop.web.busines.mapper;

import com.techshop.web.model.dto.ShoppingCartDto;
import com.techshop.web.model.entity.OrderProduct;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface OrderProductMapper extends GenericMapper<OrderProduct, ShoppingCartDto>{
    OrderProductMapper ORDER_PRODUCT_MAPPER = Mappers.getMapper(OrderProductMapper.class);
}

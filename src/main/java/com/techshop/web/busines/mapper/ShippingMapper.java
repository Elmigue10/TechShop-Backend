package com.techshop.web.busines.mapper;

import com.techshop.web.model.dto.ShippingDto;
import com.techshop.web.model.entity.Shipping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface ShippingMapper extends GenericMapper<Shipping, ShippingDto>{
    ShippingMapper SHIPPING = Mappers.getMapper(ShippingMapper.class);
}
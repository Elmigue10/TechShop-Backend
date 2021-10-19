package com.techshop.web.busines.mapper;

import com.techshop.web.model.dto.AddressDto;
import com.techshop.web.model.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressMapper extends GenericMapper<Address, AddressDto>{
    AddressMapper ADDRESS_MAPPER = Mappers.getMapper(AddressMapper.class);
}

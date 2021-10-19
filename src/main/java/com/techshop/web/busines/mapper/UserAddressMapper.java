package com.techshop.web.busines.mapper;

import com.techshop.web.model.dto.UserAddressDto;
import com.techshop.web.model.entity.UserAddress;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface UserAddressMapper extends GenericMapper<UserAddress, UserAddressDto>{
    UserAddressMapper USER_ADDRESS_MAPPER = Mappers.getMapper(UserAddressMapper.class);
}

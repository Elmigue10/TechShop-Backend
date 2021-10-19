package com.techshop.web.busines.mapper;

import com.techshop.web.model.dto.UserDto;
import com.techshop.web.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface UserMapper extends GenericMapper<User, UserDto>{
    UserMapper USER_MAPPER= Mappers.getMapper(UserMapper.class);
}

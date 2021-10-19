package com.techshop.web.busines.services;

import com.techshop.web.busines.mapper.UserAddressMapper;
import com.techshop.web.model.dto.UserAddressDto;
import com.techshop.web.model.repository.UserAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAddressService{
    @Autowired
    private UserAddressRepository userAddressRepository;

    public void saveUserAddress(UserAddressDto userAddressDto){
        userAddressRepository.save(UserAddressMapper.USER_ADDRESS_MAPPER.toEntity(userAddressDto));
    };
}

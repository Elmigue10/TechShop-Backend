package com.techshop.web.busines.services;

import com.techshop.web.busines.mapper.UserAddressMapper;
import com.techshop.web.model.dto.UserAddressDto;
import com.techshop.web.model.entity.UserAddress;
import com.techshop.web.model.repository.UserAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserAddressService{
    @Autowired
    private UserAddressRepository userAddressRepository;

    public void saveUserAddress(UserAddressDto userAddressDto){
        userAddressRepository.save(UserAddressMapper.USER_ADDRESS_MAPPER.toEntity(userAddressDto));
    };

    public Optional<List<UserAddress>> getAllAddress(Integer idUser){
        return userAddressRepository.findUserAddressById(idUser);
    }
}

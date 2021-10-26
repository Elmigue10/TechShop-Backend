package com.techshop.web.busines.services;

import com.techshop.web.busines.mapper.AddressMapper;
import com.techshop.web.busines.mapper.UserAddressMapper;
import com.techshop.web.model.dto.AddressDto;
import com.techshop.web.model.dto.UserAddressDto;
import com.techshop.web.model.entity.Address;
import com.techshop.web.model.entity.User;
import com.techshop.web.model.repository.AddressRepository;
import com.techshop.web.model.repository.UserAddressRepository;
import com.techshop.web.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserAddressRepository userAddressRepository;

    public Optional<Address> findAddressById(Integer id){
        return addressRepository.findById(id);
    }

    public List<AddressDto> getAllAddress(Integer id) throws Exception {
        List<AddressDto> addressDtoList = new ArrayList<>();
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            throw new Exception("Usuario no encontrado");
        }
        Optional<List<Address>> addressList = addressRepository.getAllByIdUser(id);
        for (Address address:addressList.get()){
            addressDtoList.add(AddressMapper.ADDRESS_MAPPER.toDto(address));
        }
        return addressDtoList;
    }

    public void saveAddress(AddressDto addressDto){
        Address address = AddressMapper.ADDRESS_MAPPER.toEntity(addressDto);
        addressRepository.save(address);
        userAddressRepository.save(
                UserAddressMapper.USER_ADDRESS_MAPPER.toEntity(
                        UserAddressDto.builder()
                                .idUser(addressDto.getIdUser())
                                .idAddress(address.getId())
                                .build()
                ));
    }

    public void updateAddress(AddressDto addressDto){
        addressRepository.updateByIdUser(addressDto.getIdUser(),addressDto.getAddress(),addressDto.getCity(), addressDto.getRegion(), addressDto.getPhone());
    }

    public void deleteAddress(AddressDto addressDto){
        addressRepository.deleteByIdUser(addressDto.getAddress(),addressDto.getIdUser());
    }

    Address findIdByAddress(String address){
        return addressRepository.findIdByAddress(address);
    }
}

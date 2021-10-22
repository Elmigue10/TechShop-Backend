package com.techshop.web.busines.services;

import com.techshop.web.model.dto.AddressDto;
import com.techshop.web.model.dto.OrderHistoryDto;
import com.techshop.web.model.dto.SummaryShipping;
import com.techshop.web.model.dto.UserAddressDto;
import com.techshop.web.model.entity.*;
import com.techshop.web.model.repository.ShippingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShippingService {
    @Autowired
    private ShippingRepository shippingRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserAddressService userAddressService;
    @Autowired
    private OrderHistoryService orderHistoryService;
    @Autowired
    private AddressService addressService;


    public void save(Shipping shipping){
        shippingRepository.save(shipping);
    }

    public Optional<List<Shipping>> findShippingByIdAddress(Integer idAddress){
        return shippingRepository.findShippingByIdAddress(idAddress);
    }

    public List<SummaryShipping> findSummaryShipping(String username){
        List<SummaryShipping> summaryShippingList = new ArrayList<>();
        User user = userService.findUserByUserName(username);
        Optional<List<UserAddress>> userAddressList =  userAddressService.getAllAddress(user.getId());
        System.out.println(userAddressList.get().size());
        for(UserAddress userAddress:userAddressList.get()){
            Optional<List<Shipping>> shippingList = findShippingByIdAddress(userAddress.getIdAddress());
            for(Shipping shipping:shippingList.get()){
                Optional<Address> address = addressService.findAddressById(shipping.getIdAddress());
                Optional<OrderHistory> orderHistory = orderHistoryService.findOrderHistory(shipping.getIdOrderHistory());
                summaryShippingList.add(
                        SummaryShipping.builder()
                                .sentAt(shipping.getSentAt())
                                .address(address.get().getAddress())
                                .idOrder(orderHistory.get().getId())
                                .totalPrice(orderHistory.get().getTotalPrice())
                                .build()
                );
            }
        }
        return summaryShippingList;
    }
}

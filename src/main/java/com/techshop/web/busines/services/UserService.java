package com.techshop.web.busines.services;

import com.techshop.web.busines.mapper.UserMapper;
import com.techshop.web.model.dto.UserDto;
import com.techshop.web.model.dto.UserNamePassword;
import com.techshop.web.model.entity.User;
import com.techshop.web.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    public void save(UserDto userDto) {
        userRepository.save(UserMapper.USER_MAPPER.toEntity(
                UserDto.builder()
                        .name(userDto.getName())
                        .username(userDto.getUsername())
                        .email(userDto.getEmail())
                        .password(bcryptEncoder.encode(userDto.getPassword()))
                        .enable(userDto.getEnable())
                        .build()
        ));
    }

    public void updateUser(UserDto userDto){
        userRepository.updateByUserName(userDto.getUsername(),userDto.getName(),userDto.getEmail(), bcryptEncoder.encode(userDto.getPassword()));
    }

    public void updatePassword(UserNamePassword userNamePassword){
        userRepository.updatePasswordByUserName(userNamePassword.getUsername(), bcryptEncoder.encode(userNamePassword.getPassword()));
    }

    public Optional<User> findById(int id){
        return userRepository.findById(id);
    }

}

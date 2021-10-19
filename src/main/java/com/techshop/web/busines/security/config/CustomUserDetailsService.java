package com.techshop.web.busines.security.config;

import java.util.ArrayList;

import com.techshop.web.busines.mapper.UserMapper;
import com.techshop.web.model.dto.UserDto;
import com.techshop.web.model.entity.User;
import com.techshop.web.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }

    public User save(UserDto userDto) {
        /*
        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        return userRepository.save(newUser);
        */

        return userRepository.save(UserMapper.USER_MAPPER.toEntity(
                UserDto.builder()
                        .name(userDto.getName())
                        .username(userDto.getUsername())
                        .email(userDto.getEmail())
                        .password(bcryptEncoder.encode(userDto.getPassword()))
                        .enable(userDto.getEnable())
                        .build()
                ));
    }

}
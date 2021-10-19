package com.techshop.web.busines.controller;


import com.techshop.web.busines.mapper.MHelpers;
import com.techshop.web.busines.mapper.UserMapper;
import com.techshop.web.busines.security.config.CustomUserDetailsService;
import com.techshop.web.busines.security.config.JwtUtil;
import com.techshop.web.busines.services.UserService;
import com.techshop.web.model.dto.*;
import com.techshop.web.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/techshop/web/v1")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    private LoginResponse loginResponse = new LoginResponse();

    @PostMapping(path = "/authenticate",  consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
            throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtUtil.generateToken(userDetails);
        String nombreToken = jwtUtil.getUsernameFromToken(token);
        String idToken = jwtUtil.getIdFromToken(token);

        loginResponse.setId(idToken);
        loginResponse.setNombre(nombreToken);
        loginResponse.setToken(token);

        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(customUserDetailsService.save(userDto));
    }

    @PostMapping("/updateToken")
    public ResponseEntity<UserDto> updateToken (@RequestBody TokenDto token){
        int userid = Integer.parseInt(jwtUtil.getIdFromToken(token.getToken()));
        Optional<User> user = userService.findById(userid);
        return ResponseEntity.ok(UserDto.builder()
                .username(user.get().getUsername())
                .name(user.get().getName())
                .enable(user.get().getEnable())
                .email(user.get().getEmail())
                .build());
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}

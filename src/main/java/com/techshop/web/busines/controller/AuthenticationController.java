package com.techshop.web.busines.controller;


import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.techshop.web.busines.mapper.MHelpers;
import com.techshop.web.busines.mapper.UserMapper;
import com.techshop.web.busines.security.config.CustomUserDetailsService;
import com.techshop.web.busines.security.config.JwtUtil;
import com.techshop.web.busines.services.UserService;
import com.techshop.web.model.dto.*;
import com.techshop.web.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
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

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping(value = "/techshop/web/v1")
public class AuthenticationController {

    @Value("${google.clientId}")
    String googleClientId;

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

    @PostMapping("/google")
    public ResponseEntity<?> google(@RequestBody TokenDto tokenDto) throws Exception {
        String password=null;
        String username=null;
        UserDto user=null;
        final NetHttpTransport transport = new NetHttpTransport();
        final JacksonFactory jacksonFactory = JacksonFactory.getDefaultInstance();
        GoogleIdTokenVerifier.Builder verifier =
                new GoogleIdTokenVerifier.Builder(transport, jacksonFactory)
                        .setAudience(Collections.singletonList(googleClientId));
        final GoogleIdToken googleIdToken = GoogleIdToken.parse(verifier.getJsonFactory(), tokenDto.getToken());
        final GoogleIdToken.Payload payload = googleIdToken.getPayload();

        if(userService.existsEmail(payload.getEmail())) {
            user = userService.findUserByEmail(payload.getEmail());
            authenticate(payload.getEmail(), password);
            username=user.getUsername();
        }else{
            user = userService.createUserGmail(payload);
            authenticate(payload.getEmail(), password);
            username=user.getUsername();
        }

        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(payload.getEmail());

        final String token = jwtUtil.generateToken(userDetails);
        String nombreToken = jwtUtil.getUsernameFromToken(token);
        String idToken = jwtUtil.getIdFromToken(token);

        loginResponse.setId(idToken);
        loginResponse.setNombre(nombreToken);
        loginResponse.setToken(token);
        
        return ResponseEntity.ok(loginResponse);
    }
}

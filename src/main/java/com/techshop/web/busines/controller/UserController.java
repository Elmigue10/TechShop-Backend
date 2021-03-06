package com.techshop.web.busines.controller;

import com.techshop.web.busines.security.config.CustomUserDetailsService;
import com.techshop.web.busines.services.UserService;
import com.techshop.web.model.dto.EmailDto;
import com.techshop.web.model.dto.UserDto;
import com.techshop.web.model.dto.UserNamePassword;
import com.techshop.web.model.entity.User;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("techshop/web/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<UserDto> getUSer(@PathVariable("username") String username){
        return new ResponseEntity(userService.findUserDtoByUserName(username),HttpStatus.OK );
    }

    @PostMapping(value = "/save")
    public ResponseEntity saveUser(@RequestBody UserDto userDto) {
        userService.save(userDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity updateUser(@RequestBody UserDto userDto){
        userService.updateUser(userDto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/password")
    public ResponseEntity updatePassword(@RequestBody UserNamePassword userNamePassword){
        userService.updatePassword(userNamePassword);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/mail/{username}")
    public ResponseEntity getMailPassword(@PathVariable("username") String username){
        userService.sendMailMail(username);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}

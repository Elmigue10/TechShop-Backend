package com.techshop.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/hellouser")
    public String getUser()
    {
        return "Hello User";
    }

    @RequestMapping("/helloadmin")
    public String getAdmin()
    {
        return "Hello Admin";
    }

}

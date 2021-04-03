package com.wifeblog.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/profile")
    public String profilePage(){
        return "users/profile";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "users/login";
    }

}

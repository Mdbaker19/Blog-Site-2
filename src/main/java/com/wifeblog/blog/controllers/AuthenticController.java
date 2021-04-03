package com.wifeblog.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticController {

    @GetMapping("/register")
    public String registerForm(){
        return "auth/register";
    }

}

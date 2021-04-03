package com.wifeblog.blog.controllers;

import com.wifeblog.blog.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticController {

    private final PasswordEncoder encoder;
    private final UserRepository userDao;

    public AuthenticController(PasswordEncoder encoder, UserRepository userDao){
        this.encoder = encoder;
        this.userDao = userDao;
    }

    @GetMapping("/register")
    public String registerForm(){
        return "auth/register";
    }

}

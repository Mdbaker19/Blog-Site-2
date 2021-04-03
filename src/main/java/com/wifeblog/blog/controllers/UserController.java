package com.wifeblog.blog.controllers;

import com.wifeblog.blog.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    private final PasswordEncoder encoder;
    private final UserRepository userDao;

    public UserController(PasswordEncoder encoder, UserRepository userDao){
        this.encoder = encoder;
        this.userDao = userDao;
    }

    @GetMapping("/profile")
    public String profilePage(){
        return "users/profile";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "users/login";
    }


}

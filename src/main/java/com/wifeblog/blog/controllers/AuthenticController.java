package com.wifeblog.blog.controllers;

import com.wifeblog.blog.model.User;
import com.wifeblog.blog.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthenticController {

    private final PasswordEncoder encoder;
    private final UserRepository userDao;

    public AuthenticController(PasswordEncoder encoder, UserRepository userDao){
        this.encoder = encoder;
        this.userDao = userDao;
    }

    @GetMapping("/register")
    public String registerForm(Model model){
        model.addAttribute("user", new User());
        return "auth/register";
    }


    @PostMapping("/register")
    public String postRegister(@ModelAttribute @Validated User user, Errors validation,
                               @RequestParam(name = "cpass", required = false) String confirm){

        return "redirect:/";
//        return "redirect:/profile";
    }

}

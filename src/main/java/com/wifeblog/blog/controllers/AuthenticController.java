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

import java.util.ArrayList;
import java.util.List;

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
    public String postRegister(@ModelAttribute @Validated User user, Errors validation, Model model,
                               @RequestParam(name = "confirmPass", required = false) String confirm){

        List<String> errorList = new ArrayList<>();
        if(userDao.findByUsername(user.getUsername()) != null) {
            validation.rejectValue("username", "username already in use");
            errorList.add("username already in use");
        }
        if(userDao.findByEmail(user.getEmail()) != null) {
            validation.rejectValue("email", "email already in use");
            errorList.add("email already in use");
        }
        if(!user.getPassword().equals(confirm)) {
            validation.rejectValue("confirm", "Passwords don not match");
            errorList.add("Passwords don not match");
        }
        if(validation.hasErrors()) {
            model.addAttribute("errorList", errorList);
            return "auth/register";
        }
        user.setPassword(encoder.encode(user.getPassword()));
        userDao.save(user);
        return "redirect:/profile";
    }

}

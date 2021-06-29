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
        if(user.getUsername().isEmpty()) {
            validation.rejectValue("username", "Username can not be empty");
            errorList.add("Username can not be empty");
        }
        if(validation.hasErrors()) {
            model.addAttribute("errorList", errorList);
            return "auth/register";
        }
        user.setPassword(encoder.encode(user.getPassword()));
        user.setAnsweredList("");
        user.setFavoriteList("");;
        user.setProfileImage("https://i.pinimg.com/236x/8c/70/8b/8c708b478e0e71f7599b75b9cc108ddf.jpg"); // will be some default decided later
        userDao.save(user);
        return "redirect:/profile";
    }

}

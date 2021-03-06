package com.wifeblog.blog.controllers;

import com.wifeblog.blog.repository.PostRepository;
import com.wifeblog.blog.util.Methods;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    private final PostRepository postDao;

    public MainController(PostRepository postDao){
        this.postDao = postDao;
    }

    @GetMapping("/")
    public String homePage(){
        return "home";
    }

}

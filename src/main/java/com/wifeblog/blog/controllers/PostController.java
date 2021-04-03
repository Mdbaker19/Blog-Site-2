package com.wifeblog.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {

    @GetMapping("/posts")
    public String allPosts(){
        return "posts/index";
    }

    @GetMapping("/posts/1")
    public String thisPost(){
        return "posts/postShow";
    }

}

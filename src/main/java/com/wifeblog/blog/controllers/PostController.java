package com.wifeblog.blog.controllers;

import com.wifeblog.blog.repository.CommentRepository;
import com.wifeblog.blog.repository.PostRepository;
import com.wifeblog.blog.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {

    private final CommentRepository commentDao;
    private final UserRepository userDao;
    private final PostRepository postDao;

    public PostController(CommentRepository commentDao, UserRepository userDao, PostRepository postDao){
        this.commentDao = commentDao;
        this.userDao = userDao;
        this.postDao = postDao;
    }

    @GetMapping("/posts")
    public String allPosts(){
        return "posts/index";
    }

    @GetMapping("/posts/1")
    public String thisPost(){
        return "posts/postShow";
    }

}

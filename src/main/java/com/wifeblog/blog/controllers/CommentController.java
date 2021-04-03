package com.wifeblog.blog.controllers;

import com.wifeblog.blog.repository.CommentRepository;
import com.wifeblog.blog.repository.PostRepository;
import com.wifeblog.blog.repository.UserRepository;
import org.springframework.stereotype.Controller;

@Controller
public class CommentController {

    private final CommentRepository commentDao;
    private final UserRepository userDao;
    private final PostRepository postDao;

    public CommentController(CommentRepository commentDao, UserRepository userDao, PostRepository postDao){
        this.commentDao = commentDao;
        this.userDao = userDao;
        this.postDao = postDao;
    }

}

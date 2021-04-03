package com.wifeblog.blog.controllers;

import com.wifeblog.blog.model.Comment;
import com.wifeblog.blog.repository.CommentRepository;
import com.wifeblog.blog.repository.PostRepository;
import com.wifeblog.blog.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @PostMapping("/approve/{id}")
    public String approveComment(@PathVariable(name = "id") long id){
        Comment currComment = commentDao.getOne(id);
        currComment.setIsApproved(1);
        commentDao.save(currComment);
        return "redirect:/"; // current url later
    }

}

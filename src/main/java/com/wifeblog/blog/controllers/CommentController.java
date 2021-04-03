package com.wifeblog.blog.controllers;

import com.wifeblog.blog.model.Comment;
import com.wifeblog.blog.repository.CommentRepository;
import com.wifeblog.blog.repository.PostRepository;
import com.wifeblog.blog.repository.UserRepository;
import com.wifeblog.blog.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Timestamp;
import java.util.Date;

@Controller
public class CommentController {

    private final CommentRepository commentDao;
    private final UserRepository userDao;
    private final UserService userService;
    private final PostRepository postDao;

    public CommentController(CommentRepository commentDao, UserService userService, UserRepository userDao, PostRepository postDao){
        this.commentDao = commentDao;
        this.userService = userService;
        this.userDao = userDao;
        this.postDao = postDao;
    }

    @PostMapping("/comment/{id}")
    public String postComment(@ModelAttribute Comment comment, @PathVariable(name = "id") long id){
        comment.setIsApproved(0);
        comment.setPost(postDao.getOne(id));
        comment.setCreatedAt(new Timestamp(new Date().getTime()));
        comment.setUser(userService.getLoggedInUser());
        commentDao.save(comment);
        return "redirect:/"; // current url later
    }

    @PostMapping("/approve/{id}")
    public String approveComment(@PathVariable(name = "id") long id){
        Comment currComment = commentDao.getOne(id);
        currComment.setIsApproved(1);
        commentDao.save(currComment);
        return "redirect:/"; // current url later
    }

}

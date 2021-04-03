package com.wifeblog.blog.controllers;

import com.wifeblog.blog.model.Post;
import com.wifeblog.blog.model.User;
import com.wifeblog.blog.repository.CommentRepository;
import com.wifeblog.blog.repository.PostRepository;
import com.wifeblog.blog.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.*;

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
    public String allPosts(Model model){
        List<Post> allPosts = postDao.findAll();
        model.addAttribute("posts", allPosts);
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String thisPost(@PathVariable(name = "id") long id, Model model){
        model.addAttribute("post", postDao.getOne(id));
        return "posts/postShow";
    }

    @PostMapping("/like/{id}")
    public String likePost(@PathVariable(name = "id") long id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        String currList = user.getFavoriteList();
        if(currList == null) currList = "";
        currList+=String.valueOf(id);
        user.setFavoriteList(currList);
        userDao.save(user);
        return "redirect:/profile"; //will be curr url later
    }

}

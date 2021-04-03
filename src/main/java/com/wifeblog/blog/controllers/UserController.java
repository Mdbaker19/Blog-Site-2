package com.wifeblog.blog.controllers;

import com.wifeblog.blog.model.Post;
import com.wifeblog.blog.model.User;
import com.wifeblog.blog.repository.PostRepository;
import com.wifeblog.blog.repository.UserRepository;
import com.wifeblog.blog.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class UserController {

    private final PasswordEncoder encoder;
    private final UserRepository userDao;
    private final PostRepository postDao;
    private final UserService userService;

    public UserController(PasswordEncoder encoder, UserRepository userDao, UserService userService, PostRepository postDao){
        this.encoder = encoder;
        this.postDao = postDao;
        this.userDao = userDao;
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String profilePage(Model model, HttpSession session){
        User user = userService.getLoggedInUser();
        session.setAttribute("user", user);
        List<Post> likedList = new ArrayList<>();
        if(user.getFavoriteList() != null) {
            String[] likes = user.getFavoriteList().split("");
            for (String l : likes) {
                likedList.add(postDao.getOne(Long.parseLong(l)));
            }
            session.setAttribute("userLikes", likedList);
        }
        return "users/profile";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "users/login";
    }


}

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
        if(user.getFavoriteList() != null && user.getFavoriteList().length() > 0) {
            String[] likes = user.getFavoriteList().split("%");
            for (String l : likes) {
                likedList.add(postDao.getOne(Long.parseLong(l)));
            }
        }
        session.setAttribute("userLikes", likedList);
        return "users/profile";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "users/login";
    }

    @GetMapping("/auth/{id}")
    public String authActivateForNow(@PathVariable(name = "id") long id){
        User user = userDao.findById(id).get();
        user.setIsAuthenticated(1);
        userDao.save(user);
        return "redirect:/login?activated";
    }

    @GetMapping("/settings")
    public String settingsPage(Model model){
        model.addAttribute("user", userService.getLoggedInUser());
        return "users/settings";
    }

    @PostMapping("/settings")
    public String updateSettings(@ModelAttribute User user){

        return "redirect:/profile";
    }


}

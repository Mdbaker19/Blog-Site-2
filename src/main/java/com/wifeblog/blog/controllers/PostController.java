package com.wifeblog.blog.controllers;

import com.wifeblog.blog.model.Category;
import com.wifeblog.blog.model.Post;
import com.wifeblog.blog.model.User;
import com.wifeblog.blog.repository.*;
import com.wifeblog.blog.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.*;

@Controller
public class PostController {

    private final CommentRepository commentDao;
    private final UserRepository userDao;
    private final CategoryRepository categoryDao;
    private final PostRepository postDao;
    private final UserService userService;
    private final QuestionRepository questionDao;

    public PostController(CommentRepository commentDao, CategoryRepository categoryDao, UserService userService, QuestionRepository questionDao, UserRepository userDao, PostRepository postDao){
        this.commentDao = commentDao;
        this.categoryDao = categoryDao;
        this.userService = userService;
        this.questionDao = questionDao;
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

    @GetMapping("/create")
    public String createForm(Model model){
        User user = userService.getLoggedInUser();
        if(user.getIsAdmin() < 1) return "redirect:/";
        model.addAttribute("post", new Post());
        model.addAttribute("categoryList", categoryDao.findAll());
        return "posts/create";
    }

    @PostMapping("/create")
    public String createPost(@RequestParam(name = "categories", required = false) String categories, @ModelAttribute Post post){
        post.setCreatedAt(new Timestamp(new Date().getTime()));
        post.setLikeCount(0);

        List<Category> categoryList = new ArrayList<>();
        String[] catList = categories.split(",");

        for(String c : catList) {
            Long catId = Long.parseLong(c) + 1;
            categoryList.add(categoryDao.getOne(catId));
        }

        post.setCategories(categoryList);
        postDao.save(post);
        return "redirect:/";
    }


    @PostMapping("/like/{id}")
    public String likePost(@PathVariable(name = "id") long id, HttpSession session) {
        User user = userService.getLoggedInUser();
        String currList = user.getFavoriteList();
        if(currList == null) currList = "";
        currList+=String.valueOf(id);
        user.setFavoriteList(currList);
        userDao.save(user);
        return "redirect:/profile"; //will be curr url later
    }

}

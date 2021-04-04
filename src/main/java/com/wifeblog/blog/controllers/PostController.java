package com.wifeblog.blog.controllers;

import com.wifeblog.blog.model.Category;
import com.wifeblog.blog.model.Comment;
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
    public String thisPost(@PathVariable(name = "id") long id, Model model, HttpSession session){
        User user = userService.getLoggedInUser();
        session.setAttribute("user", user);
        model.addAttribute("post", postDao.getOne(id));
        model.addAttribute("comment", new Comment());
        boolean likedYet = false;
        String likeString = user.getFavoriteList();
        if(likeString.length() > 0) {
            List<Long> likes = new ArrayList<>();
            String[] likeList = likeString.split("%");
            for (String s : likeList) {
                likes.add(Long.parseLong(s));
            }
            if (likes.contains(id)) likedYet = true;
        }
        model.addAttribute("likedAlready", likedYet);
        return "posts/postShow";
    }

    @GetMapping("/create")
    public String createForm(Model model){
        User user = userService.getLoggedInUser();
        if(user.getIsAdmin() < 1) return "redirect:/";
        model.addAttribute("post", new Post());
        List<Category> categoryList = categoryDao.findAll();
        model.addAttribute("categoryList", categoryList);
        return "posts/create";
    }

    @PostMapping("/create")
    public String createPost(@RequestParam(name = "categories", required = false) String categories, @RequestParam(name = "newCategories", required = false) String newCategories, @ModelAttribute Post post){
        post.setCreatedAt(new Timestamp(new Date().getTime()));
        post.setLikeCount(0);

        List<Category> categoryList = new ArrayList<>();

        if(newCategories != null) {
            String[] otherCategories = newCategories.split(", ");
            for(String category : otherCategories) {
                createCategory(category);
            }
            for(String category : otherCategories) {
                categoryList.add(categoryDao.findByName(category));
            }
        }

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
    public String likePost(@PathVariable(name = "id") long id) {
        User user = userService.getLoggedInUser();

        Post currPost = postDao.getOne(id);
        int likeCount = currPost.getLikeCount();
        likeCount++;
        currPost.setLikeCount(likeCount);
        postDao.save(currPost);

        String currList = user.getFavoriteList();
        if(currList == null) currList = "";
        currList+=id+"%";
        user.setFavoriteList(currList);
        userDao.save(user);
        return "redirect:/profile"; //will be curr url later
    }

    @PostMapping("/dislike/{id}")
    public String dislikePost(@PathVariable(name = "id") long id) {
        User user = userService.getLoggedInUser();
        String likeString = user.getFavoriteList();
        StringBuilder newLikes = new StringBuilder();
        if(likeString.length() > 0) {
            String[] likes = likeString.split("%");
            for (String l : likes) {
                long iD = Long.parseLong(l);
                if (iD != id) {
                    newLikes.append(iD);
                    newLikes.append("%");
                }
            }
        }
        user.setFavoriteList(newLikes.toString());
        userDao.save(user);
        return "redirect:/profile";
    }









    private void createCategory(String categoryName) {
        Category category = new Category();
        category.setName(categoryName);
        categoryDao.save(category);
    }

}

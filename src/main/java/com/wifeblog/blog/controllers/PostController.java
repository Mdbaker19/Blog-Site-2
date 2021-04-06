package com.wifeblog.blog.controllers;

import com.wifeblog.blog.model.*;
import com.wifeblog.blog.repository.*;
import com.wifeblog.blog.service.UserService;
import com.wifeblog.blog.util.Methods;
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
    private final QuestionRepository questionDao;
    private final UserService userService;
    private final Methods methods = new Methods();


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

    @GetMapping("/finish/{id}")
    public String finishPost(@PathVariable(name = "id") long id){
        return "redirect:/posts/" + id;
    }


    @GetMapping("/posts/{id}")
    public String thisPost(@PathVariable(name = "id") long id, Model model, HttpSession session){
        User user = null;
        model.addAttribute("post", postDao.getOne(id));
        try {
            user = userService.getLoggedInUser();
        } catch (Exception ignored){}
        if(user == null) {
            model.addAttribute("timeFormat", methods);
            return "posts/postPartial";
        }
        session.setAttribute("user", user);
        session.setAttribute("timeFormat", methods);

        String answers = user.getAnsweredList();
        boolean canView = alreadySeenQuestion(user, id);
        if (canView) {
            boolean likedYet = likesPostAlready(user, id);
            model.addAttribute("likedAlready", likedYet);

            model.addAttribute("comment", new Comment());
            return "posts/postShow";
        } else {
            List<Question> allQs = questionDao.findAll();
            int ran = (int) (Math.random() * allQs.size());
            answers += id + "%";
            user.setAnsweredList(answers);

            userDao.save(user);
            model.addAttribute("postId", id);
            model.addAttribute("question", allQs.get(ran));
            return "question/view";
        }

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

    private boolean likesPostAlready(User user, long id) {
        boolean likedYet = false;
        String likeString = user.getFavoriteList();
        if (likeString.length() > 0) {
            List<Long> likes = new ArrayList<>();
            String[] likeList = likeString.split("%");
            for (String s : likeList) {
                likes.add(Long.parseLong(s));
            }
            if (likes.contains(id)) likedYet = true;
        }
        return likedYet;
    }

    private boolean alreadySeenQuestion(User user, long id){
        boolean canView = false;
        String answers = user.getAnsweredList();
        if (answers.length() > 0) {
            String[] ansArr = answers.split("%");
            List<Long> ansList = new ArrayList<>();
            for (String a : ansArr) {
                ansList.add(Long.parseLong(a));
            }
            if (ansList.contains(id)) {
                canView = true;
            }
        }
        return canView;
    }

}

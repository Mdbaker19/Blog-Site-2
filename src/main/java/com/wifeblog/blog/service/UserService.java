package com.wifeblog.blog.service;

import com.wifeblog.blog.model.User;
import com.wifeblog.blog.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Security;

@Service
public class UserService {
    private final UserRepository usersDao;

    public UserService(UserRepository usersDao){this.usersDao = usersDao;}

    public User getLoggedInUser(){
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return usersDao.findById(loggedInUser.getId()).get();
    }
}
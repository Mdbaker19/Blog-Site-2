package com.wifeblog.blog.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int(11) unsigned")
    private long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column
    private String favoriteList;

    // A list of posts that the user has answered and can then view later
    @Column
    private String answeredList;

    @Column(columnDefinition = "text")
    private String profileImage;

    @Column(nullable = false, columnDefinition = "TINYINT")
    private int isAdmin;

    @Column(columnDefinition = "TINYINT")
    private int isAuthenticated;

    @Column
    private String authCode;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Comment> comments;


    public User(){}

    public User(User copy){
        this.id = copy.id;
        this.username = copy.username;
        this.email = copy.email;
        this.password = copy.password;
        this.favoriteList = copy.favoriteList;
        this.profileImage = copy.profileImage;
        this.isAdmin = copy.isAdmin;
        this.isAuthenticated = copy.isAuthenticated;
        this.authCode = copy.authCode;
        this.comments = copy.comments;
        this.answeredList = copy.answeredList;
    }

    public User(long id, String answeredList, String username, String email, String password, String favoriteList, String profileImage, int isAdmin, int isAuthenticated, String authCode, List<Comment> comments) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.favoriteList = favoriteList;
        this.profileImage = profileImage;
        this.isAdmin = isAdmin;
        this.isAuthenticated = isAuthenticated;
        this.authCode = authCode;
        this.comments = comments;
        this.answeredList = answeredList;
    }

    public User(String username, String answeredList, String email, String password, String favoriteList, String profileImage, int isAdmin, int isAuthenticated, String authCode, List<Comment> comments) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.favoriteList = favoriteList;
        this.profileImage = profileImage;
        this.isAdmin = isAdmin;
        this.isAuthenticated = isAuthenticated;
        this.authCode = authCode;
        this.comments = comments;
        this.answeredList = answeredList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFavoriteList() {
        return favoriteList;
    }

    public void setFavoriteList(String favoriteList) {
        this.favoriteList = favoriteList;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public int getIsAuthenticated() {
        return isAuthenticated;
    }

    public void setIsAuthenticated(int isAuthenticated) {
        this.isAuthenticated = isAuthenticated;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getAnsweredList() {
        return answeredList;
    }

    public void setAnsweredList(String answeredList) {
        this.answeredList = answeredList;
    }

}

package com.wifeblog.blog.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.*;

@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int(11) unsigned")
    private long id;

    @Column(nullable = false)
    private String title;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "questions")
    private List<Post> postList = new ArrayList<>();

    @Column(nullable = false)
    private Timestamp createdAt;

    public Question(){}

    public Question(long id, String title, Timestamp createdAt, List<Post> postList) {
        this.id = id;
        this.createdAt = createdAt;
        this.title = title;
        this.postList = postList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}

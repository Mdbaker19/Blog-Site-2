package com.wifeblog.blog.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int(11) unsigned")
    private long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "categories")
    private List<Post> postList = new ArrayList<>();

    public Category(){}

    public Category(long id, String name, List<Post> postList) {
        this.id = id;
        this.name = name;
        this.postList = postList;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }
}

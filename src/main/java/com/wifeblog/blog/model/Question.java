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

    @ManyToMany()
    @JoinTable(
            name = "question_answers",
            joinColumns = {@JoinColumn(name = "question_id")},
            inverseJoinColumns = {@JoinColumn(name = "answer_id")})
    private List<Answer> answerList = new ArrayList<>();

    public Question(){}

    public Question(long id, String title, Timestamp createdAt, List<Post> postList, List<Answer> answerList) {
        this.id = id;
        this.createdAt = createdAt;
        this.answerList = answerList;
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

    public List<Answer> getAnswer() {
        return answerList;
    }

    public void setAnswer(List<Answer> answerList) {
        this.answerList = answerList;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}

package com.wifeblog.blog.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "answers")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int(11) unsigned")
    private long id;

    @Column(nullable = false, columnDefinition = "text")
    private String content;

    @ManyToOne
    private User user;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "answerList")
    private List<Question> questionList = new ArrayList<>();

    @Column(nullable = false)
    private Timestamp answeredAt;

    public Answer () {}

    public Answer(long id, String content, Timestamp answeredAt, User user, List<Question> questionList) {
        this.id = id;
        this.content = content;
        this.user = user;
        this.questionList = questionList;
        this.answeredAt = answeredAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Question> getQuestion() {
        return questionList;
    }

    public void setQuestion(List<Question> questionList) {
        this.questionList = questionList;
    }

    public Timestamp getAnsweredAt() {
        return answeredAt;
    }

    public void setAnsweredAt(Timestamp answeredAt) {
        this.answeredAt = answeredAt;
    }
}

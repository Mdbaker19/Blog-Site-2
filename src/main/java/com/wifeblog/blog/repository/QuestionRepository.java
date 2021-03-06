package com.wifeblog.blog.repository;

import com.wifeblog.blog.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findAll();
}

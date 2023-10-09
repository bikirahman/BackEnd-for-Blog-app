package com.blog.api.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.api.entity.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{
}

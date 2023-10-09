package com.blog.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.blog.api.entity.Category;
import com.blog.api.entity.Posts;
import com.blog.api.entity.User;
import com.blog.api.payload.PostsDto;

public interface PostsRepo extends JpaRepository<Posts, Integer> {

	List<Posts> findByUser(User user);

	List<Posts> findByCategory(Category category);

	//List<Posts> findByTitleContaining(String title);

	@Query("SELECT p FROM Posts p WHERE p.title LIKE %:keyword%")
	public List<Posts> search(@Param("keyword") String title);
}

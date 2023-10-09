package com.blog.api.payload;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.blog.api.entity.Category;
import com.blog.api.entity.Comment;
import com.blog.api.entity.User;

public class PostsDto {

	private String title;
	private String content;
	private String image;
	private Date date;
	private CategoryDto category;
	private UserDto user;
	private Set<CommentDto> comments = new HashSet<>();

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public PostsDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public CategoryDto getCategory() {
		return category;
	}

	public void setCategory(CategoryDto category) {
		this.category = category;
	}

	public UserDto getUser() {
		return user;
	}

	public Set<CommentDto> getComments() {
		return comments;
	}

	public void setComments(Set<CommentDto> commentDto) {
		this.comments = commentDto;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

}

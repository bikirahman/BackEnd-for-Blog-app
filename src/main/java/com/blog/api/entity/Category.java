package com.blog.api.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Categories")
public class Category {

	@jakarta.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	private String categoryName;
	private String categoryDesc;

	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private List<Posts> posts = new ArrayList<>();

	public int getId() {
		return Id;
	}

	public Category(int id, String categoryName, String categoryDesc, List<Posts> posts) {
		super();
		Id = id;
		this.categoryName = categoryName;
		this.categoryDesc = categoryDesc;
		this.posts = posts;
	}

	public List<Posts> getPosts() {
		return posts;
	}

	public void setPosts(List<Posts> posts) {
		this.posts = posts;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryDesc() {
		return categoryDesc;
	}

	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}

	public Category() {
		super();
	}

}

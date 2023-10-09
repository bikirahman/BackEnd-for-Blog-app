package com.blog.api.payload;

public class CategoryDto {

	private int Id;
	private String categoryName;
	private String categoryDesc;

	public int getId() {
		return Id;
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

	public CategoryDto(int id, String categoryName, String categoryDesc) {
		super();
		Id = id;
		this.categoryName = categoryName;
		this.categoryDesc = categoryDesc;
	}

	public CategoryDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}

package com.blog.api.payload;

public class CommentDto {

	private int Id;
	private String content;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public CommentDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}

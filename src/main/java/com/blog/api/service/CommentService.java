package com.blog.api.service;

import java.util.List;

import com.blog.api.payload.CommentDto;

public interface CommentService {

	CommentDto createComments(CommentDto commentDto, Integer postId);
	void deleteComment(Integer commentId);
}

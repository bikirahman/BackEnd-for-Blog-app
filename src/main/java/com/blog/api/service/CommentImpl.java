package com.blog.api.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.blog.api.entity.Comment;
import com.blog.api.entity.Posts;
import com.blog.api.exception.ResourceNotFoundException;
import com.blog.api.payload.CommentDto;
import com.blog.api.repositories.CommentRepo;
import com.blog.api.repositories.PostsRepo;

@Service
public class CommentImpl implements CommentService {

	@Autowired
	private CommentRepo commentRepo;

	@Autowired
	private PostsRepo postsRepo;

	@Autowired
	private ModelMapper mapper;

	@Override
	public CommentDto createComments(CommentDto commentDto, Integer postId) {
		// TODO Auto-generated method stub
		
		Posts posts = this.postsRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Posts", "post id", postId));
		Comment comment = this.mapper.map(commentDto, Comment.class);
		comment.setPosts(posts);
		Comment commentRes = this.commentRepo.save(comment);
		return this.mapper.map(commentRes, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		// TODO Auto-generated method stub
		Comment comment = this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "comment id", commentId));
		this.commentRepo.delete(comment);
	}

}

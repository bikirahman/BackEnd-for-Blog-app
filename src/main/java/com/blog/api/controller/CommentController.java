package com.blog.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.api.payload.CommentDto;
import com.blog.api.service.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@PostMapping("/comments/create/{postId}")
	public ResponseEntity<CommentDto> create(@RequestBody CommentDto commentDto, @PathVariable Integer postId) {
		CommentDto cDto = this.commentService.createComments(commentDto, postId);
		return new ResponseEntity<CommentDto>(cDto, HttpStatus.CREATED);
	}
}

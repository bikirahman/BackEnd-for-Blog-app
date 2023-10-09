package com.blog.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.api.payload.PostResponse;
import com.blog.api.payload.PostsDto;
import com.blog.api.repositories.PostsRepo;
import com.blog.api.service.PostsService;

@RestController
@RequestMapping("/api")
public class PostsController {

	@Autowired
	private PostsService postsService;

	@PostMapping("/users/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostsDto> createPost(@RequestBody PostsDto postsDto, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {
		PostsDto postsDtoRes = this.postsService.createPosts(postsDto, userId, categoryId);
		return new ResponseEntity<PostsDto>(postsDtoRes, HttpStatus.CREATED);
	}

	@GetMapping("/users/{id}/posts")
	public ResponseEntity<List<PostsDto>> getPostsByUsers(@PathVariable Integer id) {
		List<PostsDto> postsDto = this.postsService.getPostsByUserId(id);
		return new ResponseEntity<List<PostsDto>>(postsDto, HttpStatus.OK);
	}

	@GetMapping("/category/{id}/posts")
	public ResponseEntity<List<PostsDto>> getPostsByCategory(@PathVariable Integer id) {
		List<PostsDto> postsDtos = this.postsService.getPostsByCategoryId(id);
		return new ResponseEntity<List<PostsDto>>(postsDtos, HttpStatus.OK);
	}

	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy) {
		PostResponse postResponse = this.postsService.getAllPosts(pageNumber, pageSize, sortBy);
		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deletePost(@PathVariable Integer id) {
		this.postsService.deletePosts(id);
		return new ResponseEntity<String>("Post deleted!", HttpStatus.OK);
	}

	@PutMapping("/update/{id}/posts/")
	public ResponseEntity<PostsDto> updatePosts(@RequestBody PostsDto postsDto, @PathVariable Integer id) {
		PostsDto postsDto2 = this.postsService.updatePosts(postsDto, id);
		return new ResponseEntity<PostsDto>(postsDto2, HttpStatus.OK);
	}

	@GetMapping("/posts/{id}")
	public ResponseEntity<PostsDto> getPostsById(@PathVariable Integer id) {
		PostsDto postsDto = this.postsService.getPostsById(id);
		System.out.println(postsDto);
		return new ResponseEntity<PostsDto>(postsDto, HttpStatus.OK);
	}

	@GetMapping("/posts/search/{search}")
	public ResponseEntity<List<PostsDto>> findSearch(@PathVariable String search) {
		List<PostsDto> postsDtos = this.postsService.searchPosts(search);
		return new ResponseEntity<List<PostsDto>>(postsDtos, HttpStatus.OK);
	}
}

package com.blog.api.service;

import java.util.List;

import com.blog.api.entity.Posts;
import com.blog.api.payload.PostResponse;
import com.blog.api.payload.PostsDto;

public interface PostsService {

	PostsDto createPosts(PostsDto postsDto, Integer UserId, Integer CategoryId);

	PostsDto updatePosts(PostsDto postsDto, Integer id);

	PostsDto getPostsById(Integer id);

	void deletePosts(Integer id);

	PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy);

	List<PostsDto> getPostsByCategoryId(Integer cInteger);

	List<PostsDto> getPostsByUserId(Integer uInteger);

	List<PostsDto> searchPosts(String keyword);
}
package com.blog.api.service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.blog.api.constants.AppConstants;
import com.blog.api.entity.Category;
import com.blog.api.entity.Posts;
import com.blog.api.entity.User;
import com.blog.api.exception.ResourceNotFoundException;
import com.blog.api.payload.PostResponse;
import com.blog.api.payload.PostsDto;
import com.blog.api.repositories.CategoryRepo;
import com.blog.api.repositories.CommentRepo;
import com.blog.api.repositories.PostsRepo;
import com.blog.api.repositories.UserRepo;

@Service
public class PostsImpl implements PostsService {

	@Autowired
	private PostsRepo postsRepo;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private CommentRepo commentRepo;

	@Override
	public PostsDto createPosts(PostsDto postsDto, Integer userId, Integer categoryId) {

		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstants.USER, AppConstants.USER_ID, userId));

		Category category = categoryRepo.findById(categoryId).orElseThrow(
				() -> new ResourceNotFoundException(AppConstants.CATEGORY, AppConstants.CATEGORY_ID, categoryId));

		Posts posts = this.mapper.map(postsDto, Posts.class);
		posts.setImage("default.png");
		posts.setDate(new Date(0));
		posts.setUser(user);
		posts.setCategory(category);

		Posts resPosts = this.postsRepo.save(posts);
		System.out.println("result data : " + resPosts);

		return this.mapper.map(resPosts, PostsDto.class);
	}

	@Override
	public PostsDto updatePosts(PostsDto postsDto, Integer id) {
		// TODO Auto-generated method stub
		Posts posts = this.postsRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstants.POST, AppConstants.POST_ID, id));
		posts.setImage(postsDto.getImage());
		posts.setTitle(postsDto.getTitle());
		posts.setContent(postsDto.getContent());

		Posts resPosts = this.postsRepo.save(posts);
		return this.mapper.map(resPosts, PostsDto.class);
	}

	@Override
	public PostsDto getPostsById(Integer id) {
		// TODO Auto-generated method stub
		Posts posts = this.postsRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstants.POST, AppConstants.POST_ID, id));
		System.out.println("comment : "+posts);
		return this.mapper.map(posts, PostsDto.class);
	}

	@Override
	public void deletePosts(Integer id) {
		// TODO Auto-generated method stub
		Posts posts = this.postsRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstants.POST, AppConstants.POST_ID, id));
		this.postsRepo.delete(posts);
	}

	@Override
	public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
		Page<Posts> pagePost = this.postsRepo.findAll(pageable);
		// TODO Auto-generated method stub
		List<Posts> posts = pagePost.getContent();
		List<PostsDto> postsDtos = posts.stream().map(post -> this.mapper.map(post, PostsDto.class))
				.collect(Collectors.toList());

		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postsDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setLastPage(pagePost.isLast());
		return postResponse;
	}

	@Override
	public List<PostsDto> getPostsByCategoryId(Integer cInteger) {
		// TODO Auto-generated method stub
		Category category = this.categoryRepo.findById(cInteger).orElseThrow(
				() -> new ResourceNotFoundException(AppConstants.CATEGORY, AppConstants.CATEGORY_ID, cInteger));
		List<Posts> posts = this.postsRepo.findByCategory(category);
		List<PostsDto> postsDtos = posts.stream().map(post -> this.mapper.map(post, PostsDto.class))
				.collect(Collectors.toList());
		return postsDtos;
	}

	@Override
	public List<PostsDto> getPostsByUserId(Integer uInteger) {
		User user = this.userRepo.findById(uInteger)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstants.USER, AppConstants.USER_ID, uInteger));
		List<Posts> posts = this.postsRepo.findByUser(user);
		List<PostsDto> postsDtos = posts.stream().map(post -> this.mapper.map(post, PostsDto.class))
				.collect(Collectors.toList());
		return postsDtos;
	}

	@Override
	public List<PostsDto> searchPosts(String keyword) {
		// TODO Auto-generated method stub
		List<Posts> posts = this.postsRepo.search(keyword);
		List<PostsDto> postsDtos = posts.stream().map(post -> this.mapper.map(post, PostsDto.class))
				.collect(Collectors.toList());
		return postsDtos;
	}

}

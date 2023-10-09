package com.blog.api.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.api.entity.Category;
import com.blog.api.exception.ResourceNotFoundException;
import com.blog.api.payload.CategoryDto;
import com.blog.api.repositories.CategoryRepo;

@Service
public class CategoryImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private ModelMapper mapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		// TODO Auto-generated method stub
		Category category = this.mapper.map(categoryDto, Category.class);
		Category catResCategory = this.categoryRepo.save(category);
		return this.mapper.map(catResCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer catId) {
		// TODO Auto-generated method stub
		Category category = this.categoryRepo.findById(catId).orElseThrow(()->new ResourceNotFoundException("Category", "Category Id", catId));
		
		category.setCategoryName(categoryDto.getCategoryName());
		category.setCategoryDesc(categoryDto.getCategoryDesc());
		
		Category catResCategory = this.categoryRepo.save(category);
		return this.mapper.map(catResCategory, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer id) {
		// TODO Auto-generated method stub
		Category category = this.categoryRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Category", "Category Id", id));
		this.categoryRepo.delete(category);
	}

	@Override
	public CategoryDto getCategory(Integer id) {
		// TODO Auto-generated method stub 
		Category category = this.categoryRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Category", "Category Id", id));
		return this.mapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategoriesCategory() {
		// TODO Auto-generated method stub
		List<Category> categories = this.categoryRepo.findAll();
		List<CategoryDto> categoryDtos = categories.stream().map((cat)->this.mapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		return categoryDtos;
	}

}

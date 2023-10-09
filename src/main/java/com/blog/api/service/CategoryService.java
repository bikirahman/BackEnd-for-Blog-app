package com.blog.api.service;

import java.util.List;

import com.blog.api.payload.CategoryDto;

public interface CategoryService {

	CategoryDto createCategory(CategoryDto categoryDto);
	CategoryDto updateCategory (CategoryDto categoryDto, Integer caInteger);
	void deleteCategory (Integer catId);
	CategoryDto getCategory (Integer catId);
	List<CategoryDto> getAllCategoriesCategory ();
}

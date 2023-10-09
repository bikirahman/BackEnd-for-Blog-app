package com.blog.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.api.payload.CategoryDto;
import com.blog.api.repositories.CategoryRepo;
import com.blog.api.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	//create categories
	@PostMapping("/create")
	public ResponseEntity<CategoryDto> create(@RequestBody CategoryDto categoryDto){
		CategoryDto cDto = this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto> (cDto,HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<CategoryDto> update(@RequestBody CategoryDto categoryDto, @PathVariable("id") Integer id){
		CategoryDto cDto = this.categoryService.updateCategory(categoryDto, id);
		return new ResponseEntity<CategoryDto> (cDto,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Integer id){
		this.categoryService.deleteCategory(id);
		return new ResponseEntity<String> ("Successfully deleted!",HttpStatus.OK);
	}
	
	@GetMapping("/fetch/{id}")
	public ResponseEntity<CategoryDto> getCategories(@PathVariable("id") Integer id){
		CategoryDto cDto = this.categoryService.getCategory(id);
		return new ResponseEntity<CategoryDto> (cDto,HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategories(){
		List<CategoryDto> getAllCategory = this.categoryService.getAllCategoriesCategory();
		return new ResponseEntity<List<CategoryDto>> (getAllCategory,HttpStatus.OK);
	}
	
}

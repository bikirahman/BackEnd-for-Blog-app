package com.blog.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.blog.api.payload.UserDto;
import com.blog.api.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService service;

	// creating user
	@PostMapping("/create")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
		UserDto createDto = this.service.createUser(userDto);
		return new ResponseEntity<UserDto>(createDto, HttpStatus.CREATED);
	}

	// updating user
	@PutMapping("/update/{userId}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable("userId") Integer id) {
		UserDto resDto = this.service.updateUser(userDto, id);
		return new ResponseEntity<UserDto>(resDto, HttpStatus.OK);
	}

	// get users
	@GetMapping("/fetchuser/{id}")
	public ResponseEntity<UserDto> getUser(@PathVariable("id") Integer userid) {
		UserDto userDto = this.service.getUserById(userid);
		return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
	}

	// get all users
	@GetMapping("/fetchusers")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		List<UserDto> allUsersDtos = this.service.getAllUsers();
		return new ResponseEntity<List<UserDto>>(allUsersDtos, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable Integer userId) {
		this.service.deleteUser(userId);
		return new ResponseEntity<String>("User deleted!", HttpStatus.OK);
	}
}

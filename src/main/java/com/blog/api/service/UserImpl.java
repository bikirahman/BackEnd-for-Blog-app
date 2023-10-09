package com.blog.api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.blog.api.constants.AppConstants;
import com.blog.api.entity.Role;
import com.blog.api.entity.User;
import com.blog.api.exception.ResourceNotFoundException;
import com.blog.api.payload.UserDto;
import com.blog.api.repositories.RoleRepo;
import com.blog.api.repositories.UserRepo;

@Service
public class UserImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private RoleRepo roleRepo;

	@Override
	public UserDto createUser(UserDto userDto) {
		// TODO Auto-generated method stub
		User user = this.dtoToUser(userDto);
		User resUser = this.userRepo.save(user);
		return this.userToDto(resUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		user.setName(userDto.getName());
		user.setAge(userDto.getAge());
		user.setAddress(userDto.getAddress());
		user.setAbout(userDto.getAbout());

		User resUser = this.userRepo.save(user);

		return this.userToDto(resUser);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		List<User> getAllUsers = this.userRepo.findAll();
		List<UserDto> userDtos = getAllUsers.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		this.userRepo.delete(user);

	}

	public User dtoToUser(UserDto userDto) {
		User user = this.mapper.map(userDto, User.class);
//        User user = new User();
//        user.setId(userDto.getId()); // Set the Id property
//        user.setName(userDto.getName());
//        user.setAge(userDto.getAge());
//        user.setAddress(userDto.getAddress());
//        user.setAbout(userDto.getAbout());
		return user;
	}

	public UserDto userToDto(User user) {
		UserDto userDto = this.mapper.map(user, UserDto.class);
//        userDto.setId(user.getId()); // Set the Id property
//        userDto.setName(user.getName());
//        userDto.setAge(user.getAge());
//        userDto.setAddress(user.getAddress());
//        userDto.setAbout(user.getAbout());
		return userDto;
	}

	@Override
	public UserDto registerUser(UserDto userDto) {
		// TODO Auto-generated method stub
		User user = this.mapper.map(userDto, User.class);
		user.setAddress(this.bCryptPasswordEncoder.encode(user.getAddress()));
		Role role = this.roleRepo.findById(AppConstants.NORMAL_ROLE).get();
		user.getRoles().add(role);

		User resUser = this.userRepo.save(user);

		return this.mapper.map(resUser, UserDto.class);
	}

}

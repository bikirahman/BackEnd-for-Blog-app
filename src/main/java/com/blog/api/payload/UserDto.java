package com.blog.api.payload;

import java.util.HashSet;
import java.util.Set;

import com.blog.api.entity.Role;

public class UserDto {

	private int Id;
	private String name;
	private String age;
	private String address;
	private String about;
	private Set<RoleDto> roles = new HashSet<>();

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public UserDto() {
		super();
	}

	public Set<RoleDto> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleDto> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "UserDto [Id=" + Id + ", name=" + name + ", age=" + age + ", address=" + address + ", about=" + about
				+ "]";
	}

}

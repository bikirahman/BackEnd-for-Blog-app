package com.blog.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.api.entity.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {

}

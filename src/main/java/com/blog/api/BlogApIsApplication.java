package com.blog.api;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.blog.api.constants.AppConstants;
import com.blog.api.entity.Role;
import com.blog.api.repositories.RoleRepo;

@SpringBootApplication
public class BlogApIsApplication implements CommandLineRunner {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(BlogApIsApplication.class, args);
	}

	@Bean
	ModelMapper mapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(this.passwordEncoder.encode("Biki"));
		try {
			Role role = new Role();
			role.setId(AppConstants.NORMAL_ROLE);
			role.setName("NORMAL_USER");

			Role role2 = new Role();
			role2.setId(AppConstants.ADMIN_ROLE);
			role2.setName("ADMIN_USER");

			List<Role> roles = List.of(role, role2);
			this.roleRepo.saveAll(roles);

		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}
}

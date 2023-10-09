package com.blog.api.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	@Bean
	Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()

				.apis(RequestHandlerSelectors.basePackage("com.blog.api.controller")) // Replace with your controller
																						// package
				.paths(PathSelectors.any()).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfo("Backend Application", "This project is developed by Iftikar", "V1.0", "Terms of Service",
				new Contact("Iftikar", "bikirahman265@gmail.com", "Developer"), "API License",
				"http://your-api-license-url.com", // Replace with your API license URL
				Collections.emptyList());
	}
}

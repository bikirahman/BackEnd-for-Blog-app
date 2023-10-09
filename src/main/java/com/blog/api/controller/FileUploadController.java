package com.blog.api.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.blog.api.helper.FileUploadHelper;

@RestController
@RequestMapping("/api")
public class FileUploadController {

	@Autowired
	private FileUploadHelper fileUploadHelper;

	@PostMapping("/upload-file")
	private ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
		System.out.println(file.getContentType());
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getSize());
		//System.out.println(new ClassPathResource("static/images").getFile().getAbsolutePath());

		boolean res = fileUploadHelper.uploadFile(file);
		if (res) {
			System.out.println("File uploaded successfully.");
            
			return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/")
					.path(file.getOriginalFilename()).toUriString());
		} else {
			System.err.println("File upload failed.");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}

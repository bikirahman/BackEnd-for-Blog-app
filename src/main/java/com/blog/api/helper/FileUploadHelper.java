package com.blog.api.helper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelper {

	// Static variable declaration
    public static String UPLOAD_DIR;
    
    public FileUploadHelper() {
    }
   
    public boolean uploadFile(MultipartFile file) {

        boolean flag = false; // Declare and initialize the flag here
        String path = "src/main/resources/static/images/";
        try {
            Files.copy(file.getInputStream(), (new File(path + file.getOriginalFilename())).toPath(),
                    StandardCopyOption.REPLACE_EXISTING);
            flag = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return flag;
    }
}

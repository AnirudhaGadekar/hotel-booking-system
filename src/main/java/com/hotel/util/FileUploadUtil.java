package com.hotel.util;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileUploadUtil {
    
    private static final String UPLOAD_DIR = "uploadImage/";
    
    public String uploadFile(MultipartFile file, String folder, String oldFileName) throws IOException {
        if (file == null || file.isEmpty()) {
            return oldFileName;
        }
        
        // Create directory if not exists
        Path uploadPath = Paths.get(UPLOAD_DIR + folder);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        
        // Delete old file if exists
        if (oldFileName != null && !oldFileName.isEmpty()) {
            Path oldFilePath = uploadPath.resolve(oldFileName);
            Files.deleteIfExists(oldFilePath);
        }
        
        // Save new file
        String fileName = file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        
        return fileName;
    }
    
    public void deleteFile(String folder, String fileName) throws IOException {
        if (fileName != null && !fileName.isEmpty()) {
            Path filePath = Paths.get(UPLOAD_DIR + folder, fileName);
            Files.deleteIfExists(filePath);
        }
    }
}
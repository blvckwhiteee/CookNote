package com.example.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {

    @Value("${file.upload-dir}")
    private String uploadDir;


    public ResponseEntity<String> uploadImage(MultipartFile file) {
        try {
            String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filepath = Paths.get(uploadDir, filename);
            Files.createDirectories(filepath.getParent());
            Files.copy(file.getInputStream(), filepath, StandardCopyOption.REPLACE_EXISTING);
            String fileUrl = "/uploads/" + filename;
            return ResponseEntity.ok(fileUrl);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Upload error");
        }
    }
}

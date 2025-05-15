package learneverything.learning_service.application.controllers;

import learneverything.learning_service.utils.CommonUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/image")
public class UploadController {
    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) throws IOException {
        String saveFilePath = CommonUtils.saveFile("uploads", file.getOriginalFilename(), file.getBytes());

        return ResponseEntity.ok("File uploaded successfully, url: " + saveFilePath);
    }
}

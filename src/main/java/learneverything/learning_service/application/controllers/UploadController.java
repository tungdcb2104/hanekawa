package learneverything.learning_service.application.controllers;
import learneverything.learning_service.utils.CommonUtils;
import lombok.Getter;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/v1/upload")
public class UploadController {
    @PostMapping("/image")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) throws IOException {
        if (!file.isEmpty()){
            String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
            String saveFilePath = CommonUtils.saveFile("uploads", String.format(UUID.randomUUID().toString()+"."+extension), file.getBytes());

            return ResponseEntity
                    .ok("File uploaded successfully, url: " + saveFilePath);
        }else {
            return ResponseEntity
                    .badRequest()
                    .body(String.format("File %s is empty !",file.getOriginalFilename()));
        }
    }

    @GetMapping("/image/{fileName:.+}")
    public ResponseEntity<Resource> getImage(
            @PathVariable String fileName
    ) throws IOException {
        Path filePath = Paths.get("uploads/").resolve(fileName).normalize();
        Resource resource = new UrlResource(filePath.toUri());

        if (resource.exists()) {
            String contentType = Files.probeContentType(filePath);
            if (contentType == null) {
                contentType = "application/octet-stream";
            }
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

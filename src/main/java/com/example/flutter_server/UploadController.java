package com.example.flutter_server;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/upload")
public class UploadController {

    // 업로드할 경로 설정 (예: 로컬 서버에서 저장할 폴더 경로)
    String projectDir = System.getProperty("user.dir");
    String uploadDir = projectDir + "/uploads/";

    @PostMapping
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            String filePath = uploadDir + file.getOriginalFilename();

            File dest = new File(filePath);

            // 폴더가 존재하지 않으면 생성
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }

            // 파일 저장
            file.transferTo(dest);

            return new ResponseEntity<>("이미지 업로드 성공: " + filePath, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("이미지 업로드 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
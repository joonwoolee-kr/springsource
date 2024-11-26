package com.example.movie.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@RequestMapping("/upload")
@Controller
public class UploadController {

    @Value("${com.example.movie.upload.path}")
    private String uploadPath;

    @GetMapping("/upload")
    public void getUpload() {
        log.info("업로드 폼 요청");
    }

    @PostMapping("/upload")
    public void postUpload(MultipartFile[] uploadFiles) {
        log.info("업로드 요청");
        for (MultipartFile multipartFile : uploadFiles) {
            log.info("OriginalFilename {}", multipartFile.getOriginalFilename());
            log.info("Size {}", multipartFile.getSize());
            log.info("ContentType {}", multipartFile.getContentType()); // image/png

            // 이미지 파일 확인
            if (!multipartFile.getContentType().startsWith("image")) {
                return;
            }

            // 파일명
            String originName = multipartFile.getOriginalFilename();

            // 년/월/일 폴더
            String saveFolderPath = makeFolder();

            // 파일 저장: uuid(중복 파일 해결)
            String uuid = UUID.randomUUID().toString();
            // upload/2024/11/26/3f9e9302-fbcc-45e1-831e-03abf1a3a0b0_1.jpg
            String saveName = uploadPath + File.separator + saveFolderPath + File.separator + uuid + "_" + originName;

            Path savePath = Paths.get(saveName);

            try {
                // 저장
                multipartFile.transferTo(savePath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String makeFolder() {
        // 오늘 날짜 구하기
        LocalDate today = LocalDate.now();
        log.info("today {}", today); // today 2024-11-26

        // Formatter: 날짜/시간/숫자 등을 특정 포맷으로 지정
        // SimpleDateFormat sdf = new SimpleDateFormat("YYYY/mm/dd");
        // sdf.format(new Date());
        String dateStr = today.format(DateTimeFormatter.ofPattern("YYYY/MM/dd"));
        File dirs = new File(uploadPath, dateStr);
        if (!dirs.exists()) {
            dirs.mkdirs(); // 폴더 생성
        }

        // 오늘 날짜로 폴더 생성
        return dateStr;
    }

}

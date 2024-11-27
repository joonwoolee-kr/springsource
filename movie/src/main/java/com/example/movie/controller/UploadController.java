package com.example.movie.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;

import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import net.coobird.thumbnailator.Thumbnails;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.movie.dto.UploadResultDto;

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
    public ResponseEntity<List<UploadResultDto>> postUpload(MultipartFile[] uploadFiles) {
        log.info("업로드 요청");

        List<UploadResultDto> uploadResultDtos = new ArrayList<>();

        for (MultipartFile multipartFile : uploadFiles) {
            log.info("OriginalFilename {}", multipartFile.getOriginalFilename());
            log.info("Size {}", multipartFile.getSize());
            log.info("ContentType {}", multipartFile.getContentType()); // image/png

            // 이미지 파일 확인
            if (!multipartFile.getContentType().startsWith("image")) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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

                // 썸네일 저장
                String thumbSaveName = uploadPath + File.separator + saveFolderPath + File.separator + "s_" + uuid + "_"
                        + originName;
                File thumbFile = new File(thumbSaveName);
                Thumbnailator.createThumbnail(savePath.toFile(), thumbFile, 100, 100);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 저장된 파일 정보 추가
            uploadResultDtos.add(new UploadResultDto(uuid, originName, saveFolderPath));
        }

        return new ResponseEntity<List<UploadResultDto>>(uploadResultDtos, HttpStatus.OK);
    }

    @GetMapping("/display")
    public ResponseEntity<byte[]> getFile(String fileName) {
        ResponseEntity<byte[]> result = null;

        try {
            String srcFileName = URLDecoder.decode(fileName, "utf-8");
            File file = new File(uploadPath, srcFileName);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", Files.probeContentType(file.toPath())); // Content 타입 추가(image/jpeg, text/html)
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return result;
    }

    @PostMapping("/remove")
    public ResponseEntity<String> postRemove(String filePath) {
        log.info("삭제 요청 {}", filePath);
        try {
            String srcFileName = URLDecoder.decode(filePath, "utf-8");
            // 원본 파일 삭제
            File file = new File(uploadPath, srcFileName);
            file.delete();

            // 썸네일 파일 삭제
            File thumbFile = new File(file.getParent(), "s_" + file.getName());
            thumbFile.delete();

            return new ResponseEntity<String>("success", HttpStatus.OK);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
    }

    private String makeFolder() {
        // 오늘 날짜 구하기
        LocalDate today = LocalDate.now();
        log.info("today {}", today); // today 2024-11-26

        // Formatter: 날짜/시간/숫자 등을 특정 포맷으로 지정
        // SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/dd");
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

package com.example.movie.task;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.movie.dto.MovieImageDto;
import com.example.movie.entity.MovieImage;
import com.example.movie.repository.MovieImageRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class FileCheckTask {
    @Autowired
    private MovieImageRepository movieImageRepository;

    @Value("${com.example.movie.upload.path}")
    private String uploadPath;

    private String getYesterdayFolder() {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        String result = yesterday.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        return result.replace("/", File.separator);
    }

    @Scheduled(cron = "0 * * * * *")
    public void checkFile() {
        log.info("file check 메소드 실행");

        // db에서 전일자 이미지 파일 목록 추출
        List<MovieImage> oldMovieImages = movieImageRepository.findOldFileAll();
        // entity => dto
        List<MovieImageDto> movieImageDtos = oldMovieImages.stream().map(movieImage -> {
            return MovieImageDto.builder()
                    .inum(movieImage.getInum())
                    .uuid(movieImage.getUuid())
                    .imgName(movieImage.getImgName())
                    .path(movieImage.getPath())
                    .build();
        }).collect(Collectors.toList());

        // 전일자 파일 목록 추출

        // 비교 후 db와 일치하지 않는 파일 제거
    }
}
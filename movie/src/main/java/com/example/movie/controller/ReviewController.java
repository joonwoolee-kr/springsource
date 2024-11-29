package com.example.movie.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import com.example.movie.dto.ReviewDto;
import com.example.movie.service.ReviewService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequestMapping("/reviews")
@RequiredArgsConstructor
@Log4j2
@RestController
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/{mno}/all")
    public List<ReviewDto> getReviewList(@PathVariable Long mno) {
        log.info("리뷰 리스트 요청 {}", mno);
        List<ReviewDto> reviews = reviewService.getReviews(mno);
        return reviews;
    }

    @DeleteMapping("/{mno}/{reviewNo}")
    public Long removeReview(@PathVariable Long reviewNo) {
        log.info("리뷰 삭제 {}", reviewNo);
        reviewService.removeReview(reviewNo);
        return reviewNo;
    }

    @GetMapping("/{mno}/{reviewNo}")
    public ReviewDto getReview(@PathVariable Long reviewNo) {
        log.info("리뷰 요청 {}", reviewNo);
        return reviewService.getReview(reviewNo);
    }

    @PutMapping("/{mno}/{reviewNo}")
    public Long modifyReview(@PathVariable Long reviewNo, @RequestBody ReviewDto reviewDto) {
        log.info("리뷰 수정 요청 {}, {}", reviewNo, reviewDto);
        reviewDto.setReviewNo(reviewNo);
        return reviewService.modifyReview(reviewDto);
    }

    @PostMapping("/{mno}")
    public Long createReview(@RequestBody ReviewDto reviewDto) {
        log.info("리뷰 작성 요청 {}", reviewDto);
        return reviewService.addReview(reviewDto);
    }

}

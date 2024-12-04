package com.example.movie.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.movie.dto.ReviewDto;
import com.example.movie.service.ReviewService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

@RequestMapping("/reviews")
@RequiredArgsConstructor
@Log4j2
@RestController
public class ReviewController {

    private final ReviewService reviewService;

    // ~~ /reviews/45/all
    @GetMapping("/{mno}/all")
    public List<ReviewDto> getList(@PathVariable Long mno) {
        log.info("리뷰 리스트 요청 {}", mno);

        List<ReviewDto> reviews = reviewService.getReviews(mno);

        return reviews;
    }

    // ~~~/reviews/mno/reviewno + @DeleteMapping
    @PreAuthorize("authentication.name == #email")
    @DeleteMapping("/{mno}/{reviewNo}")
    public Long deteteReview(@PathVariable Long reviewNo, String email) {
        log.info("리뷰 삭제 {}", reviewNo);

        reviewService.removeReview(reviewNo);
        return reviewNo;
    }

    // ~~/reviews/mno/reviewno + @GetMapping
    @GetMapping("/{mno}/{reviewNo}")
    public ReviewDto getMethodName(@PathVariable Long reviewNo) {
        log.info("리뷰 요청 {}", reviewNo);

        ReviewDto reviewDto = reviewService.getReview(reviewNo);
        return reviewDto;
    }

    // ~~/reviews/mno/reviewno + @PutMapping + ReviewDto
    @PreAuthorize("authentication.name == #reviewDto.email")
    @PutMapping("/{mno}/{reviewNo}")
    public Long putReview(@PathVariable Long reviewNo, @RequestBody ReviewDto reviewDto) {
        log.info("리뷰 수정 {}, {}", reviewNo, reviewDto);

        reviewDto.setReviewNo(reviewNo);
        reviewNo = reviewService.modifyReview(reviewDto);

        return reviewNo;
    }

    // ~~/reviews/mno/ + @PostMapping + ReviewDto
    @PostMapping("/{mno}")
    public Long postReview(@RequestBody ReviewDto reviewDto) {
        log.info("리뷰 등록 {}", reviewDto);

        return reviewService.addReview(reviewDto);
    }

}

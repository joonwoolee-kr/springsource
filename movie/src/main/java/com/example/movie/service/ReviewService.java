package com.example.movie.service;

import java.util.List;

import com.example.movie.dto.ReviewDto;
import com.example.movie.entity.Member;
import com.example.movie.entity.Movie;
import com.example.movie.entity.Review;

public interface ReviewService {

    // movie 번호를 이용해 특정 영화의 모든 리뷰 조회
    List<ReviewDto> getReviews(Long mno);

    // 특정 리뷰 조회
    ReviewDto getReview(Long reviewNo);

    Long addReview(ReviewDto reviewDto);

    Long modifyReview(ReviewDto reviewDto);

    void removeReview(Long reviewNo);

    default ReviewDto entityToDto(Review review) {
        ReviewDto reviewDto = ReviewDto
                .builder()
                .reviewNo(review.getReviewNo())
                .grade(review.getGrade())
                .text(review.getText())
                .mno(review.getMovie().getMno())
                .mid(review.getMember().getMid())
                .email(review.getMember().getEmail())
                .nickname(review.getMember().getNickname())
                .regDate(review.getRegDate())
                .updateDate(review.getUpdateDate())
                .build();

        return reviewDto;
    }

    default Review dtoToEntity(ReviewDto reviewDto) {
        return Review.builder()
                .reviewNo(reviewDto.getReviewNo())
                .grade(reviewDto.getGrade())
                .text(reviewDto.getText())
                .member(Member.builder().mid(reviewDto.getMid()).build())
                .movie(Movie.builder().mno(reviewDto.getMno()).build())
                .build();
    }
}

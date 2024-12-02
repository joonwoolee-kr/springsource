package com.example.movie.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.movie.dto.ReviewDto;
import com.example.movie.entity.Movie;
import com.example.movie.entity.Review;
import com.example.movie.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public List<ReviewDto> getReviews(Long mno) {
        Movie movie = Movie.builder().mno(mno).build();
        List<Review> result = reviewRepository.findByMovie(movie);

        return result.stream().map(review -> entityToDto(review)).collect(Collectors.toList());
    }

    @Override
    public ReviewDto getReview(Long reviewNo) {
        return entityToDto(reviewRepository.findById(reviewNo).get());
    }

    @Override
    public Long addReview(ReviewDto reviewDto) {

        Review review = dtoToEntity(reviewDto);

        return reviewRepository.save(review).getReviewNo();
    }

    @Override
    public Long modifyReview(ReviewDto reviewDto) {

        Review review = reviewRepository.findById(reviewDto.getReviewNo()).get();
        review.setText(reviewDto.getText());
        review.setGrade(reviewDto.getGrade());
        return reviewRepository.save(review).getReviewNo();
    }

    @Override
    public void removeReview(Long reviewNo) {
        reviewRepository.deleteById(reviewNo);
    }

}

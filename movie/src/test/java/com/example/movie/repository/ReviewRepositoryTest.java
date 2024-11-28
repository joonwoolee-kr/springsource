package com.example.movie.repository;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.movie.entity.Member;
import com.example.movie.entity.Movie;
import com.example.movie.entity.Review;

import jakarta.transaction.Transactional;

@SpringBootTest
public class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Test
    public void testInsert() {
        IntStream.rangeClosed(1, 200).forEach(i -> {
            Long mid = (long) (Math.random() * 50) + 1;
            Long mno = (long) (Math.random() * 50) + 1;
            Review review = Review.builder()
                    .grade((int) (Math.random() * 5) + 1)
                    .text("review text" + i)
                    .member(Member.builder().mid(mid).build())
                    .movie(Movie.builder().mno(mno).build())
                    .build();
            reviewRepository.save(review);
        });
    }

    // @Transactional
    @Test
    public void testGet() {
        Movie movie = movieRepository.findById(48L).get();
        List<Review> list = reviewRepository.findByMovie(movie);
        // System.out.println(list);
        list.forEach(review -> {
            System.out.println(review.getText());
            System.out.println(review.getGrade());
            System.out.println(review.getMember().getNickname());
        });
    }
}

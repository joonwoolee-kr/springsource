package com.example.movie.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.movie.entity.Member;
import com.example.movie.entity.Movie;
import com.example.movie.entity.Review;

@SpringBootTest
public class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

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
}

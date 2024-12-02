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

    // 200개

    @Test
    public void testInsert() {

        IntStream.rangeClosed(1, 200).forEach(i -> {

            // 영화번호 임의 추출
            Long mno = (long) (Math.random() * 50) + 1;
            Movie movie = Movie.builder().mno(mno).build();

            // 리뷰 멤버 임의 추출
            Long mid = (long) (Math.random() * 50) + 1;
            Member member = Member.builder().mid(mid).build();

            Review review = Review.builder()
                    .grade((int) (Math.random() * 5) + 1)
                    .text("이 영화에 대한 감상평은.." + i)
                    .movie(movie)
                    .member(member)
                    .build();
            reviewRepository.save(review);

        });
    }

    // @Transactional
    @Test
    public void testGet() {
        Movie movie = movieRepository.findById(45L).get();
        List<Review> list = reviewRepository.findByMovie(movie);
        // System.out.println(list);
        list.forEach(review -> {
            System.out.println(review.getText());
            System.out.println(review.getGrade());
            System.out.println(review.getMember().getNickname());
        });
    }
}

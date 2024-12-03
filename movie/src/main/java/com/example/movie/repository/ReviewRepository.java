package com.example.movie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;

import com.example.movie.entity.Member;
import com.example.movie.entity.Movie;
import com.example.movie.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // movie_mno 를 이용해 review 제거 메서드 생성
    @Modifying
    @Query("DELETE FROM Review r WHERE r.movie = :movie")
    void deleteByMovie(Movie movie);

    // movie_mno 를 이용해 리뷰 가져오기
    @EntityGraph(attributePaths = "member", type = EntityGraphType.FETCH)
    List<Review> findByMovie(Movie movie);

    @Modifying
    @Query("DELETE FROM Review r WHERE r.member = :member")
    void deleteByMember(Member member);
}

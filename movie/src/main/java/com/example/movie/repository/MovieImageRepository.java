package com.example.movie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.movie.entity.Movie;
import com.example.movie.entity.MovieImage;
import com.example.movie.repository.total.MovieImageReviewRepository;

public interface MovieImageRepository extends JpaRepository<MovieImage, Long>, MovieImageReviewRepository {

    // movie_mno 를 이용해 movie_image 제거 메서드 생성
    @Modifying
    @Query("DELETE FROM MovieImage mi WHERE mi.movie = :movie")
    void deleteByMovie(Movie movie);

    // 어제 날짜의 영화 이미지 리스트 가져오기
    // 추출 후 스케줄러에서 사용 예정(Entity 사용 안 함)
    @Query(value = "SELECT * FROM MOVIE_IMAGE mi WHERE mi.PATH = TO_CHAR(SYSDATE - 1, 'YYYY/MM/DD')", nativeQuery = true)
    List<MovieImage> findOldFileAll();
}

package com.example.movie.repository;

import java.util.Arrays;
import java.util.UUID;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.example.movie.entity.Movie;
import com.example.movie.entity.MovieImage;

@SpringBootTest
public class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieImageRepository movieImageRepository;

    @Test
    public void testMovieInsert() {
        IntStream.rangeClosed(1, 50).forEach(i -> {
            Movie movie = Movie.builder()
                    .title("Movie " + i)
                    .build();
            movieRepository.save(movie);

            int count = (int) (Math.random() * 5) + 1;
            for (int j = 0; j < count; j++) {
                MovieImage movieImage = MovieImage.builder()
                        .uuid(UUID.randomUUID().toString())
                        .imgName("test" + j + ".jpg")
                        .movie(movie)
                        .build();
                movieImageRepository.save(movieImage);
            }
        });
    }

    @Test
    public void testListPage() {

        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("mno").descending());

        Page<Object[]> result = movieRepository.getListPage(pageRequest);

        result.forEach(r -> System.out.println(Arrays.toString(r)));
    }
}

package com.example.movie.repository;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.example.movie.entity.Movie;
import com.example.movie.entity.MovieImage;

@SpringBootTest
public class MovieImageRepositoryTest {

    @Autowired
    private MovieImageRepository movieImageRepository;

    @Test
    public void testTotalListPage() {

        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("mno").descending());

        Page<Object[]> result = movieImageRepository.getTotalList(null, null, pageRequest);

        // [Movie(mno=50, title=Movie 50), MovieImage(inum=152,
        // uuid=683e6244-299a-4576-8598-3e3f0ac87e50, imgName=test4.jpg, path=null), 7,
        // 3.0]
        for (Object[] objects : result) {
            // System.out.println(Arrays.toString(objects));
            Movie movie = (Movie) objects[0];
            MovieImage movieImage = (MovieImage) objects[1];
            Long count = (Long) objects[2];
            Double avg = (Double) objects[3];

            System.out.println(movie);
            System.out.println(movieImage);
            System.out.println(count);
            System.out.println(avg);
        }
    }

    @Test
    public void testRow() {
        List<Object[]> result = movieImageRepository.getMovieRow(1L);

        for (Object[] objects : result) {
            System.out.println(Arrays.toString(objects));
        }
    }

}

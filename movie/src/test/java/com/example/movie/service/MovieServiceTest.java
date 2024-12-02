package com.example.movie.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.movie.dto.MovieDto;
import com.example.movie.dto.PageRequestDto;
import com.example.movie.dto.PageResultDto;

@SpringBootTest
public class MovieServiceTest {

    @Autowired
    private MovieService movieService;

    @Test
    public void testGet() {
        MovieDto movieDto = movieService.get(1L);
        System.out.println(movieDto);
    }

    @Test
    public void testList() {
        PageRequestDto requestDto = PageRequestDto.builder()
                .page(1)
                .size(10)
                .type("t")
                .keyword("Movie")
                .build();
        PageResultDto<MovieDto, Object[]> result = movieService.getList(requestDto);
        System.out.println(result.getDtoList());
    }
}

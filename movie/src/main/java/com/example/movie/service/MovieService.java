package com.example.movie.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.movie.dto.MovieDto;
import com.example.movie.dto.MovieImageDto;
import com.example.movie.dto.PageRequestDto;
import com.example.movie.dto.PageResultDto;
import com.example.movie.entity.Movie;
import com.example.movie.entity.MovieImage;

public interface MovieService {
    // Create
    Long register(MovieDto movieDto);

    // R(전체조회, 페이지나누기 + 검색)
    PageResultDto<MovieDto, Object[]> getList(PageRequestDto pageRequestDto);

    // R(상세조회)
    MovieDto get(Long mno);

    // U
    Long modify(MovieDto movieDto);

    // D
    void delete(Long mno);

    default MovieDto entityToDto(Movie movie, List<MovieImage> movieImages, Long reviewCnt, Double reviewAvg) {
        MovieDto movieDto = MovieDto.builder()
                .mno(movie.getMno())
                .title(movie.getTitle())
                // .movieImageDtos(movieImages)
                .reviewAvg(reviewAvg)
                .reviewCnt(reviewCnt)
                .regDate(movie.getRegDate())
                .build();

        // MovieImage => MovieImageDto
        List<MovieImageDto> movieImageDtos = movieImages.stream().map(movieImage -> {
            return MovieImageDto.builder()
                    .inum(movieImage.getInum())
                    .uuid(movieImage.getUuid())
                    .imgName(movieImage.getImgName())
                    .path(movieImage.getPath())
                    .build();

        }).collect(Collectors.toList());

        movieDto.setMovieImageDtos(movieImageDtos);

        return movieDto;
    }

    default Map<String, Object> dtoToEntity(MovieDto movieDto) {
        return null;
    }
}

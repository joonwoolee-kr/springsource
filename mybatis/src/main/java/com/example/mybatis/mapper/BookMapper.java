package com.example.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.mybatis.dto.BookDto;
import com.example.mybatis.dto.CategoryDto;
import com.example.mybatis.dto.PageRequestDto;
import com.example.mybatis.dto.PublisherDto;

@Mapper
public interface BookMapper {
    public BookDto read(Long id);

    public List<BookDto> listAll(PageRequestDto requestDto);

    public int totalCnt(PageRequestDto requestDto);

    public int update(BookDto dto);

    public int delete(Long id);

    public List<PublisherDto> publishers();

    public List<CategoryDto> categories();

    public int create(BookDto dto);
}

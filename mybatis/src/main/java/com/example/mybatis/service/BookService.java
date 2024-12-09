package com.example.mybatis.service;

import java.util.List;

import com.example.mybatis.dto.BookDto;
import com.example.mybatis.dto.CategoryDto;
import com.example.mybatis.dto.PageRequestDto;
import com.example.mybatis.dto.PageResultDto;
import com.example.mybatis.dto.PublisherDto;

public interface BookService {

    // C
    boolean create(BookDto dto);

    // R
    BookDto getRow(Long id);

    List<BookDto> getList(PageRequestDto requestDto);

    int getTotalCnt(PageRequestDto requestDto);

    // U
    boolean update(BookDto dto);

    // D
    boolean delete(Long id);

    List<CategoryDto> getCateList();

    List<PublisherDto> getPubList();

}

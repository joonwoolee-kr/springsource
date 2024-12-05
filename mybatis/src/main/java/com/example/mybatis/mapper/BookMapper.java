package com.example.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.mybatis.dto.BookDto;
import com.example.mybatis.dto.PageRequestDto;

@Mapper
public interface BookMapper {
    public BookDto read(Long id);

    public List<BookDto> listAll(PageRequestDto requestDto);

    public int totalCnt(PageRequestDto requestDto);
}

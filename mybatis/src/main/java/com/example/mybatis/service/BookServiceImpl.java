package com.example.mybatis.service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.mybatis.dto.BookDto;
import com.example.mybatis.dto.CategoryDto;
import com.example.mybatis.dto.PageRequestDto;
import com.example.mybatis.dto.PageResultDto;
import com.example.mybatis.dto.PublisherDto;
import com.example.mybatis.mapper.BookMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookMapper bookMapper; // repository 역할

    @Override
    public boolean create(BookDto dto) {
        return bookMapper.create(dto) == 1 ? true : false;
    }

    @Override
    public BookDto getRow(Long id) {
        return bookMapper.read(id);
    }

    @Override
    public List<BookDto> getList(PageRequestDto requestDto) {
        return bookMapper.listAll(requestDto);
    }

    @Override
    public boolean update(BookDto dto) {
        return bookMapper.update(dto) == 1 ? true : false;
    }

    @Override
    public boolean delete(Long id) {
        return bookMapper.delete(id) == 1 ? true : false;
    }

    @Override
    public List<CategoryDto> getCateList() {
        return bookMapper.categories();
    }

    @Override
    public List<PublisherDto> getPubList() {
        return bookMapper.publishers();
    }

    @Override
    public int getTotalCnt(PageRequestDto requestDto) {
        return bookMapper.totalCnt(requestDto);
    }

}

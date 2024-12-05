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
    public Long create(BookDto dto) {
        // return bookRepository.save(dtoToEntity(dto)).getId();
        return null;
    }

    @Override
    public BookDto getRow(Long id) {
        // return entityToDto(bookRepository.findById(id).get());
        return null;
    }

    @Override
    public List<BookDto> getList(PageRequestDto requestDto) {
        return bookMapper.listAll(requestDto);
    }

    @Override
    public Long update(BookDto dto) {
        // Book book = bookRepository.findById(dto.getId()).get();
        // book.setPrice(dto.getPrice());
        // book.setSale_price(dto.getSalePrice());
        // return bookRepository.save(book).getId();
        return null;
    }

    @Override
    public void delete(Long id) {
        // bookRepository.deleteById(id);
    }

    @Override
    public List<CategoryDto> getCateList() {
        // List<Category> result = categoryRepository.findAll();
        // return result.stream().map(entity ->
        // entityToDto(entity)).collect(Collectors.toList());
        return null;
    }

    @Override
    public List<PublisherDto> getPubList() {
        // List<Publisher> result = publisherRepository.findAll();
        // return result.stream().map(entity ->
        // entityToDto(entity)).collect(Collectors.toList());
        return null;
    }

    @Override
    public int getTotalCnt(PageRequestDto requestDto) {
        return bookMapper.totalCnt(requestDto);
    }

}

package com.example.book.service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.book.dto.BookDto;
import com.example.book.dto.CategoryDto;
import com.example.book.dto.PageRequestDto;
import com.example.book.dto.PageResultDto;
import com.example.book.dto.PublisherDto;
import com.example.book.entity.Book;
import com.example.book.entity.Category;
import com.example.book.entity.Publisher;
import com.example.book.repository.BookRepository;
import com.example.book.repository.CategoryRepository;
import com.example.book.repository.PublisherRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final PublisherRepository publisherRepository;

    @Override
    public Long create(BookDto dto) {
        return bookRepository.save(dtoToEntity(dto)).getId();
    }

    @Override
    public BookDto getRow(Long id) {
        return entityToDto(bookRepository.findById(id).get());
    }

    @Override
    public PageResultDto<BookDto, Book> getList(PageRequestDto requestDto) {
        // 페이지 나누기 개념 없을 때
        // List<Book> result = bookRepository.findAll();
        // return result.stream().map(entity ->
        // entityToDto(entity)).collect(Collectors.toList());

        // 페이지 나누기 개념 추가
        Pageable pageable = requestDto.getPageable(Sort.by("id").descending());
        Page<Book> result = bookRepository
                .findAll(bookRepository.makePredicate(requestDto.getType(), requestDto.getKeyword()), pageable);

        Function<Book, BookDto> fn = (entity -> entityToDto(entity));

        return new PageResultDto<>(result, fn);
    }

    @Override
    public Long update(BookDto dto) {
        Book book = bookRepository.findById(dto.getId()).get();
        book.setPrice(dto.getPrice());
        book.setSale_price(dto.getSalePrice());
        return bookRepository.save(book).getId();
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<CategoryDto> getCateList() {
        List<Category> result = categoryRepository.findAll();
        return result.stream().map(entity -> entityToDto(entity)).collect(Collectors.toList());
    }

    @Override
    public List<PublisherDto> getPubList() {
        List<Publisher> result = publisherRepository.findAll();
        return result.stream().map(entity -> entityToDto(entity)).collect(Collectors.toList());
    }

}

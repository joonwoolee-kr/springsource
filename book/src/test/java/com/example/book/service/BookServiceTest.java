package com.example.book.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.book.dto.BookDto;
import com.example.book.dto.PageRequestDto;
import com.example.book.dto.PageResultDto;
import com.example.book.entity.Book;

@SpringBootTest
public class BookServiceTest {

    @Autowired
    private BookService service;

    @Transactional
    @Test
    public void textList() {
        PageRequestDto requestDto = PageRequestDto.builder()
                .page(11)
                .size(10)
                .build();

        PageResultDto<BookDto, Book> resultDto = service.getList(requestDto);

        resultDto.getDtoList().forEach(dto -> System.out.println(dto));

        System.out.println("요청 페이지 " + resultDto.getPage());
        System.out.println("목록 개수 " + resultDto.getSize());
        System.out.println("시작 페이지 " + resultDto.getStart());
        System.out.println("마지막 페이지 " + resultDto.getEnd());
        System.out.println("pageList " + resultDto.getPageList());
        System.out.println("이전 페이지 여부 " + resultDto.isPrev());
        System.out.println("다음 페이지 여부 " + resultDto.isNext());
        System.out.println("전체 페이지 " + resultDto.getTotalPage());
    }
}

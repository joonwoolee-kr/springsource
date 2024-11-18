package com.example.book.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.book.dto.BookDto;
import com.example.book.dto.CategoryDto;
import com.example.book.dto.PageRequestDto;
import com.example.book.dto.PageResultDto;
import com.example.book.dto.PublisherDto;
import com.example.book.entity.Book;
import com.example.book.service.BookService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequestMapping("/rest")
@RequiredArgsConstructor
@Log4j2
@RestController
public class BookRestController {

    private final BookService bookService;

    @GetMapping("/list")
    public ResponseEntity<PageResultDto<BookDto, Book>> getList(
            @ModelAttribute("requestDto") PageRequestDto requestDto) {
        log.info("도서 전체 목록 요청 {}", requestDto);
        PageResultDto<BookDto, Book> result = bookService.getList(requestDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> postCreate(BookDto dto) {
        log.info("도서 입력 요청 {}", dto);
        Long id = bookService.create(dto);
        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> postModify(@PathVariable Long id, BookDto dto) {
        log.info("도서 정보 수정 {}", dto);
        // get 후 post => requestDto 값 사용 가능
        dto.setId(id);
        bookService.update(dto);
        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> postDelete(@PathVariable Long id) {
        log.info("도서 삭제 {}", id);

        bookService.delete(id);

        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }
}

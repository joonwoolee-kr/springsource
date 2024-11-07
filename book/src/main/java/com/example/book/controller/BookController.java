package com.example.book.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.book.dto.BookDto;
import com.example.book.dto.CategoryDto;
import com.example.book.dto.PublisherDto;
import com.example.book.service.BookService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@RequestMapping("/book")
@Log4j2
@Controller
public class BookController {

    private final BookService bookService;

    // 도서 추가
    @GetMapping("/create")
    public void getCreate(@ModelAttribute("dto") BookDto dto, Model model) {
        log.info("도서 입력 폼 요청");

        List<CategoryDto> categoryDtos = bookService.getCateList();
        List<PublisherDto> publisherDtos = bookService.getPubList();

        model.addAttribute("cDtos", categoryDtos);
        model.addAttribute("pDtos", publisherDtos);
    }

    @PostMapping("/create")
    public String postCreate(@Valid @ModelAttribute("dto") BookDto dto, BindingResult result, Model model,
            RedirectAttributes rttr) {
        log.info("도서 입력 요청 {}", dto);
        if (result.hasErrors()) {
            List<CategoryDto> categoryDtos = bookService.getCateList();
            List<PublisherDto> publisherDtos = bookService.getPubList();

            model.addAttribute("cDtos", categoryDtos);
            model.addAttribute("pDtos", publisherDtos);

            return "/book/create";
        }

        // Service create 호출
        Long id = bookService.create(dto);
        rttr.addFlashAttribute("msg", id + "번 도서가 추가되었습니다.");

        return "redirect:list";
    }

    // 전체 목록 조회
    @GetMapping("/list")
    public void getList(Model model) {
        log.info("도서 전체 목록 요청");
        List<BookDto> list = bookService.getList();
        model.addAttribute("list", list);
    }

    // 도서 상세 조회
    @GetMapping(value = { "/read", "/modify" })
    public void getRow(Long id, Model model) {
        log.info("도서 상세 조회 요청");
        BookDto dto = bookService.getRow(id);
        model.addAttribute("dto", dto);
    }

    // 도서 정보 수정
    @PostMapping("/modify")
    public String postModify(BookDto dto, RedirectAttributes rttr) {
        log.info("도서 정보 수정 {}", dto);
        Long id = bookService.update(dto);
        rttr.addAttribute("id", id);

        return "redirect:read";
    }

    // 도서 삭제
    @PostMapping("/delete")
    public String postDelete(@RequestParam Long id) {
        log.info("도서 삭제 {}", id);
        bookService.delete(id);

        return "redirect:list";
    }

}

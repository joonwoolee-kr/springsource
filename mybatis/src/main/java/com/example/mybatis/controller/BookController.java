package com.example.mybatis.controller;

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

import com.example.mybatis.dto.BookDto;
import com.example.mybatis.dto.CategoryDto;
import com.example.mybatis.dto.PageRequestDto;
import com.example.mybatis.dto.PageResultDto;
import com.example.mybatis.dto.PublisherDto;
import com.example.mybatis.service.BookService;

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
    public void getCreate(@ModelAttribute("dto") BookDto dto, Model model,
            @ModelAttribute("requestDto") PageRequestDto requestDto) {
        log.info("도서 입력 폼 요청");

        List<CategoryDto> categoryDtos = bookService.getCateList();
        List<PublisherDto> publisherDtos = bookService.getPubList();

        model.addAttribute("cDtos", categoryDtos);
        model.addAttribute("pDtos", publisherDtos);
    }

    @PostMapping("/create")
    public String postCreate(@Valid @ModelAttribute("dto") BookDto dto, BindingResult result, Model model,
            RedirectAttributes rttr, @ModelAttribute("requestDto") PageRequestDto requestDto) {
        log.info("도서 입력 요청 {}", dto);
        List<CategoryDto> categoryDtos = bookService.getCateList();
        List<PublisherDto> publisherDtos = bookService.getPubList();

        model.addAttribute("cDtos", categoryDtos);
        model.addAttribute("pDtos", publisherDtos);
        if (result.hasErrors()) {

            return "/book/create";
        }

        // Service create 호출
        bookService.create(dto);
        rttr.addFlashAttribute("msg", "도서가 추가되었습니다.");

        rttr.addAttribute("page", 1);
        rttr.addAttribute("size", requestDto.getSize());
        rttr.addAttribute("type", requestDto.getType());
        rttr.addAttribute("keyword", requestDto.getKeyword());

        return "redirect:list";
    }

    // 전체 목록 조회
    @GetMapping("/list")
    public void getList(@ModelAttribute("requestDto") PageRequestDto requestDto, Model model) {
        log.info("도서 전체 목록 요청 {}", requestDto);
        List<BookDto> result = bookService.getList(requestDto);
        int total = bookService.getTotalCnt(requestDto);
        log.info("list {}", result);
        log.info("total {}", total);

        model.addAttribute("result", new PageResultDto<>(requestDto, total, result));
    }

    // 도서 상세 조회
    @GetMapping(value = { "/read", "/modify" })
    public void getRow(Long id, @ModelAttribute("requestDto") PageRequestDto requestDto, Model model) {
        log.info("도서 상세 조회 요청");
        BookDto dto = bookService.getRow(id);
        model.addAttribute("dto", dto);
    }

    // 도서 정보 수정
    @PostMapping("/modify")
    public String postModify(BookDto dto, @ModelAttribute("requestDto") PageRequestDto requestDto,
            RedirectAttributes rttr) {
        log.info("도서 정보 수정 {}", dto);
        // get 후 post => requestDto 값 사용 가능
        log.info("requestDto {}", requestDto);

        if (bookService.update(dto)) {
            rttr.addAttribute("id", dto.getId());
            rttr.addAttribute("page", requestDto.getPage());
            rttr.addAttribute("size", requestDto.getSize());
            rttr.addAttribute("type", requestDto.getType());
            rttr.addAttribute("keyword", requestDto.getKeyword());
            return "redirect:read";
        } else {
            return "/book/modify";
        }

    }

    // 도서 삭제
    @PostMapping("/delete")
    public String postDelete(@RequestParam Long id, @ModelAttribute("requestDto") PageRequestDto requestDto,
            RedirectAttributes rttr) {
        log.info("도서 삭제 {}", id);
        log.info("requestDto {}", requestDto);

        bookService.delete(id);

        rttr.addAttribute("page", requestDto.getPage());
        rttr.addAttribute("size", requestDto.getSize());
        rttr.addAttribute("type", requestDto.getType());
        rttr.addAttribute("keyword", requestDto.getKeyword());

        return "redirect:list";
    }

}

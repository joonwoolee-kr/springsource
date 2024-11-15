package com.example.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.board.dto.BoardDto;
import com.example.board.dto.PageRequestDto;
import com.example.board.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@RequestMapping("/board")
@Log4j2
@Controller
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public void getList(@ModelAttribute("requestDto") PageRequestDto requestDto, Model model) {
        log.info("리스트 {}", requestDto);

        model.addAttribute("result", boardService.getList(requestDto));
    }

    @GetMapping({ "/read", "/modify" })
    public void getList(@RequestParam Long bno, @ModelAttribute("requestDto") PageRequestDto requestDto, Model model) {
        log.info("상세조회 {}", bno);
        model.addAttribute("dto", boardService.read(bno));
    }

    @PostMapping("/modify")
    public String postModify(BoardDto dto, @ModelAttribute("requestDto") PageRequestDto requestDto,
            RedirectAttributes rttr) {
        log.info("수정 {}", dto);
        Long bno = boardService.update(dto);
        rttr.addFlashAttribute("msg", bno);
        rttr.addAttribute("bno", bno);
        rttr.addAttribute("page", requestDto.getPage());
        rttr.addAttribute("size", requestDto.getSize());
        rttr.addAttribute("type", requestDto.getType());
        rttr.addAttribute("keyword", requestDto.getKeyword());

        return "redirect:read";
    }

}

package com.example.guestbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.guestbook.dto.GuestbookDto;
import com.example.guestbook.dto.PageRequestDto;
import com.example.guestbook.service.GuestbookService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@RequestMapping("/guestbook")
@Log4j2
@Controller
public class GuestbookController {

    private final GuestbookService guestbookService;

    @GetMapping("/list")
    public void getList(@ModelAttribute("requestDto") PageRequestDto requestDto, Model model) {
        log.info("list");

        model.addAttribute("result", guestbookService.list(requestDto));
    }

    @GetMapping({ "/read", "/modify" })
    public void getRow(Long gno, @ModelAttribute("requestDto") PageRequestDto requestDto, Model model) {
        log.info("상세 조회, 수정 폼 {}", gno);

        model.addAttribute("dto", guestbookService.read(gno));
    }

    @PostMapping("/modify")
    public String postModify(GuestbookDto guestbookDto, @ModelAttribute("requestDto") PageRequestDto requestDto,
            RedirectAttributes rttr) {
        log.info("수정 {}", guestbookDto);
        Long gno = guestbookService.update(guestbookDto);
        rttr.addAttribute("gno", gno);
        rttr.addAttribute("page", requestDto.getPage());
        rttr.addAttribute("size", requestDto.getSize());
        rttr.addAttribute("type", requestDto.getType());
        rttr.addAttribute("keyword", requestDto.getKeyword());

        return "redirect:read";
    }

    @PostMapping("/remove")
    public String postRemove(Long gno, @ModelAttribute("requestDto") PageRequestDto requestDto,
            RedirectAttributes rttr) {
        log.info("삭제 {}", gno);
        guestbookService.delete(gno);
        rttr.addAttribute("page", requestDto.getPage());
        rttr.addAttribute("size", requestDto.getSize());
        rttr.addAttribute("type", requestDto.getType());
        rttr.addAttribute("keyword", requestDto.getKeyword());

        return "redirect:list";
    }

    @GetMapping("/register")
    public void getRegister(@ModelAttribute("dto") GuestbookDto dto) {
        log.info("작성 폼");
    }

    @PostMapping("/register")
    public String postRegister(@Valid @ModelAttribute("dto") GuestbookDto dto, BindingResult result,
            RedirectAttributes rttr) {
        log.info("작성 {}", dto);
        if (result.hasErrors())
            return "/guestbook/register";

        Long gno = guestbookService.register(dto);

        rttr.addFlashAttribute("msg", gno);
        rttr.addAttribute("page", 1);
        rttr.addAttribute("size", 10);
        rttr.addAttribute("type", "");
        rttr.addAttribute("keyword", "");

        return "redirect:list";
    }

}

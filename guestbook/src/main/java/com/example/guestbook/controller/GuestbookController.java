package com.example.guestbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.guestbook.dto.GuestbookDto;
import com.example.guestbook.dto.PageRequestDto;
import com.example.guestbook.dto.PageResultDto;
import com.example.guestbook.entity.Guestbook;
import com.example.guestbook.service.GuestbookService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@RequestMapping("/guestbook")
@Log4j2
@Controller
public class GuestbookController {

    private final GuestbookService guestbookService;

    @GetMapping("/list")
    public void getList(PageRequestDto requestDto, Model model) {
        log.info("list 요청");
        PageResultDto<GuestbookDto, Guestbook> result = guestbookService.list(requestDto);
        model.addAttribute("result", result);
    }

}

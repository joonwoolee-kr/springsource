package com.example.movie.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.movie.dto.PageRequestDto;

@Log4j2
@Controller
public class HomeController {

    @GetMapping("/")
    public String getHome() {
        return "redirect:/movie/list";
    }

    @GetMapping("/access-denied")
    public String getError(@ModelAttribute("requestDto") PageRequestDto pageRequestDto) {
        // 403
        return "/except/denied";
    }

    @GetMapping("/error")
    public String get404(@ModelAttribute("requestDto") PageRequestDto pageRequestDto) {
        // 컨트롤러에 없는 경로 요청 시
        return "/except/url404";
    }
}

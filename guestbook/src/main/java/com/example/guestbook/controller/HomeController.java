package com.example.guestbook.controller;

import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Log4j2
@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        log.info("home 요청");
        return "redirect:/guestbook/list";
    }

}
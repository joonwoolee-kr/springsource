package com.example.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
@Controller
public class HomeController {

    @GetMapping("/")
    public String getList() {
        log.info("home");
        return "redirect:board/list";
    }

}

package com.example.movie.controller;

import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Log4j2
@RequestMapping("/movie")
@Controller
public class MovieController {

    @GetMapping("/list")
    public void getList() {
        log.info("전체 movie list 요청");
    }

}

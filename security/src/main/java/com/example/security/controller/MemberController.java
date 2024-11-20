package com.example.security.controller;

import org.springframework.stereotype.Controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/member")
@Log4j2
@Controller
public class MemberController {

    @GetMapping("/login")
    public void getLogin() {
        log.info("로그인 폼 요청");
    }

}

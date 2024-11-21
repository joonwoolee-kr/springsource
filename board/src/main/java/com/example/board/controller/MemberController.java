package com.example.board.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.board.dto.MemberDto;

@RequiredArgsConstructor
@Log4j2
@RequestMapping("/member")
@Controller
public class MemberController {

    // @PreAuthorize("permitAll()")
    @GetMapping("/login")
    public void getLogin() {
        log.info("로그인 폼 요청");
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/register")
    public void getRegister(MemberDto mDto) {
        log.info("회원가입 폼 요청");
    }

}

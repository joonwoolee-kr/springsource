package com.example.club.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.club.dto.ClubMemberDto;
import com.example.club.service.ClubUserService;

import jakarta.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/users")
@Log4j2
@Controller
public class UserController {

    private final ClubUserService clubUserService;

    @PreAuthorize("permitAll()")
    @GetMapping("/login")
    public void getLogin() {
        log.info("login 페이지 요청");
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/member")
    public void getMember() {
        log.info("member 페이지 요청");
    }

    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    @GetMapping("/admin")
    public void getAdmin() {
        log.info("admin 페이지 요청");
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/register")
    public void getRegister(@ModelAttribute("dto") ClubMemberDto dto) {
        log.info("register 페이지 요청");
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/register")
    public String postRegister(@Valid @ModelAttribute("dto") ClubMemberDto dto, BindingResult result,
            RedirectAttributes attr) {
        log.info("register 요청 {}", dto);

        if (result.hasErrors()) {
            return "/users/register";
        }

        String email = clubUserService.register(dto);

        attr.addFlashAttribute("email", email);

        return "redirect:/users/login";
    }

    @PreAuthorize("permitAll()")
    @ResponseBody
    @GetMapping("auth")
    public Authentication getAuthentication() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        return authentication;
    }

}

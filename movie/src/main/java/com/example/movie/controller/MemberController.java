package com.example.movie.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.movie.dto.AuthMemberDto;
import com.example.movie.dto.MemberDto;
import com.example.movie.dto.PageRequestDto;
import com.example.movie.dto.PasswordDto;
import com.example.movie.service.MemberService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Log4j2
@RequiredArgsConstructor
@RequestMapping("/member")
@Controller
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public void getLogin(@ModelAttribute("requestDto") PageRequestDto pageRequestDto) {
        log.info("로그인 폼 요청");
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public void getProfile(@ModelAttribute("requestDto") PageRequestDto pageRequestDto) {
        log.info("프로필 폼 요청");
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/edit")
    public void getEdit(@ModelAttribute("requestDto") PageRequestDto pageRequestDto) {
        log.info("프로필 수정 폼 요청");
    }

    // 닉네임 수정
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/edit/nickname")
    public String postNickName(MemberDto memberDto) {
        log.info("닉네임 수정 {}", memberDto);

        // email 가져오기
        Authentication authentication = getAuthentication();
        // MemberDto 에 들어있는 값 접근 시
        AuthMemberDto authMemberDto = (AuthMemberDto) authentication.getPrincipal();
        memberDto.setEmail(authMemberDto.getUsername());
        memberService.nickNickUpdate(memberDto);

        // SecurityContext 에 보관된 값 업데이트
        authMemberDto.getMemberDto().setNickname(memberDto.getNickname());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "redirect:/member/profile";
    }

    // 비밀번호 수정
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/edit/password")
    public String postPassowrdUpdate(PasswordDto passwordDto, HttpSession session, RedirectAttributes rttr) {
        log.info("비밀번호 수정 {}", passwordDto);

        // 서비스 호출
        try {
            memberService.passwordUpdate(passwordDto);
        } catch (Exception e) {
            // 실패 시 /edit
            e.printStackTrace();
            rttr.addFlashAttribute("error", e.getMessage());
            return "redirect:/member/edit";
        }
        // 성공 시 세션 해제 후 /login 이동
        session.invalidate();
        return "redirect:/member/login";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/leave")
    public void getLeave(@ModelAttribute("requestDto") PageRequestDto pageRequestDto) {
        log.info("회원 탈퇴 폼 요청");
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/leave")
    public String postLeave(PasswordDto passwordDto, boolean check, HttpSession session, RedirectAttributes rttr) {
        log.info("회원 탈퇴 요청 {}, {}", passwordDto, check);
        if (!check) {
            rttr.addFlashAttribute("error", "체크 표시를 확인해 주세요.");
            return "redirect:/member/leave";
        }

        try {
            memberService.leave(passwordDto);
        } catch (Exception e) {
            e.printStackTrace();
            rttr.addFlashAttribute("error", e.getMessage());
            return "redirect:/member/leave";
        }

        session.invalidate();

        return "redirect:/movie/list";
    }

    // 회원가입
    @GetMapping("/register")
    public void getRegister(MemberDto memberDto, @ModelAttribute("requestDto") PageRequestDto pageRequestDto) {
        log.info("회원가입 폼 요청");
    }

    @PostMapping("/register")
    public String postRegister(@Valid MemberDto memberDto, BindingResult result, boolean check,
            @ModelAttribute("requestDto") PageRequestDto pageRequestDto, Model model) {
        log.info("회원가입 요청 {}", memberDto);

        if (result.hasErrors()) {
            return "/member/register";
        }

        if (!check) {
            model.addAttribute("check", "약관에 동의하셔야 합니다.");
            return "/member/register";
        }

        memberService.register(memberDto);

        return "redirect:/member/login";
    }

    // 개발자용 - Authentication 확인용
    @PreAuthorize("isAuthenticated()")
    @ResponseBody
    @GetMapping("/auth")
    public Authentication getAuthentication() {

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        return authentication;
    }

}

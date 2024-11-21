package com.example.board.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.board.dto.MemberDto;
import com.example.board.entity.Member;
import com.example.board.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
@Service
public class BoardUserDetailsService implements UserDetailsService, BoardUserService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("login {}", username);
        return null;
    }

    @Override
    public String register(MemberDto mDto) {
        return null;
    }

    // 중복 이메일 검사
    private void validateDuplicationMember(String email) throws IllegalStateException {
        Optional<Member> result = memberRepository.findById(email);

        if (result.isPresent()) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

}

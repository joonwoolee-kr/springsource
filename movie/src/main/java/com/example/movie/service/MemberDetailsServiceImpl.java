package com.example.movie.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.movie.dto.AuthMemberDto;
import com.example.movie.dto.MemberDto;
import com.example.movie.dto.PasswordDto;
import com.example.movie.entity.Member;
import com.example.movie.entity.constant.MemberRole;
import com.example.movie.repository.MemberRepository;
import com.example.movie.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
@Service
public class MemberDetailsServiceImpl implements UserDetailsService, MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final ReviewRepository reviewRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("service username : {}", username);

        // 로그인 요청
        Optional<Member> result = memberRepository.findByEmail(username);

        if (!result.isPresent()) {
            throw new UsernameNotFoundException("이메일 확인");
        }

        // 이메일이 존재한다면 entity => dto 변경
        Member member = result.get();

        MemberDto memberDto = MemberDto.builder()
                .mid(member.getMid())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .password(member.getPassword())
                .role(member.getRole())
                .build();

        return new AuthMemberDto(memberDto);
    }

    @Transactional
    @Override
    public void nickNickUpdate(MemberDto memberDto) {

        memberRepository.updateNickName(memberDto.getNickname(), memberDto.getEmail());
    }

    @Override
    public void passwordUpdate(PasswordDto passwordDto) throws Exception {
        // email 을 이용해 사용자 찾기
        // Optional<Member> result =
        // memberRepository.findByEmail(passwordDto.getEmail());
        // if (!result.isPresent()) throw new UsernameNotFoundException("이메일 확인");

        Member member = memberRepository.findByEmail(passwordDto.getEmail()).get();

        // 현재 비밀번호(db에 저장된 값)가 입력 비밀번호(입력값)와 일치하는지 검증
        if (!passwordEncoder.matches(passwordDto.getCurrentPassword(), member.getPassword())) {
            // false : 되돌려 보내기
            throw new Exception("현재 비밀번호를 확인해 주세요");
        } else {
            // true : 새 비밀번호로 수정
            member.setPassword(passwordEncoder.encode(passwordDto.getNewPassword()));
            memberRepository.save(member);
        }
    }

    @Override
    public String register(MemberDto memberDto) {
        Member member = dtoToEntity(memberDto);
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        return memberRepository.save(member).getNickname();
    }

    @Transactional
    @Override
    public void leave(PasswordDto passwordDto) throws Exception {
        Member member = memberRepository.findByEmail(passwordDto.getEmail()).get();
        if (!passwordEncoder.matches(passwordDto.getCurrentPassword(), member.getPassword())) {
            throw new Exception("현재 비밀번호를 확인해 주세요");
        }

        reviewRepository.deleteByMember(member);
        memberRepository.deleteById(member.getMid());
    }

}

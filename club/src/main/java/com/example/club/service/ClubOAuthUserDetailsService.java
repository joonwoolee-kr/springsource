package com.example.club.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.example.club.dto.ClubAuthMemberDto;
import com.example.club.entity.ClubMember;
import com.example.club.entity.constant.ClubRole;
import com.example.club.repository.ClubMemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
@Service
public class ClubOAuthUserDetailsService extends DefaultOAuth2UserService {

    private final ClubMemberRepository clubMemberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("userRequest {}", userRequest);

        OAuth2User oAuth2User = super.loadUser(userRequest);
        oAuth2User.getAttributes().forEach((k, v) -> {
            log.info("{}: {}", k, v);
        });

        ClubMember clubMember = saveSocialClubMember(oAuth2User.getAttribute("email"));

        ClubAuthMemberDto clubAuthMemberDto = new ClubAuthMemberDto(clubMember.getEmail(),
                clubMember.getPassword(),
                clubMember.isFromSocial(),
                clubMember.getRoles()
                        .stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                        .collect(Collectors.toList()),
                oAuth2User.getAttributes());

        return clubAuthMemberDto;
    }

    // 소셜 로그인 정보를 이용한 회원가입
    private ClubMember saveSocialClubMember(String email) {
        // 소셜 로그인 계정으로 가입한 회원 존재 여부 확인
        Optional<ClubMember> result = clubMemberRepository.findByEmailAndFromSocial(email, true);

        // 소셜 로그인 계정으로 가입한 회원 있는 경우
        if (result.isPresent()) {
            return result.get();
        }

        // 소셜 로그인 계정으로 가입한 회원 없는 경우
        ClubMember clubMember = ClubMember.builder()
                .email(email)
                .name(email)
                .password(passwordEncoder.encode("1111"))
                .fromSocial(true)
                .build();

        clubMember.addMemberRole(ClubRole.USER);

        clubMemberRepository.save(clubMember);

        return clubMember;
    }
}

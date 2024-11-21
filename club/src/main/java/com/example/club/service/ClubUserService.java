package com.example.club.service;

import com.example.club.dto.ClubMemberDto;

public interface ClubUserService {
    // 회원가입
    String register(ClubMemberDto dto);
}

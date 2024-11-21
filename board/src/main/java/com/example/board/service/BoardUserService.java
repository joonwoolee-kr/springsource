package com.example.board.service;

import com.example.board.dto.MemberDto;
import com.example.board.entity.Member;
import com.example.board.repository.MemberRepository;

public interface BoardUserService {

    // 회원가입
    String register(MemberDto mDto);

    // dto => entity
    default Member dtoToEntity(MemberDto mdto) {
        return Member.builder()
                .email(mdto.getEmail())
                .name(mdto.getName())
                .password(mdto.getPassword())
                .role(mdto.getRole())
                .build();
    }

    // entity => dto
    default MemberDto entityToDto(Member entity) {
        return MemberDto.builder()
                .email(entity.getEmail())
                .name(entity.getName())
                .password(entity.getPassword())
                .role(entity.getRole())
                .build();
    }
}

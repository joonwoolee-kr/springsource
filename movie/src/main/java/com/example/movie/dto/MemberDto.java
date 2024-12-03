package com.example.movie.dto;

import java.time.LocalDateTime;

import com.example.movie.entity.constant.MemberRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MemberDto {
    private Long mid;

    @Email
    @NotBlank(message = "이메일을 입력해주세요")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요")
    private String password;
    @NotBlank(message = "닉네임을 입력해주세요")
    private String nickname;

    private MemberRole role;

    private LocalDateTime regDate;
    private LocalDateTime updateDate;
}

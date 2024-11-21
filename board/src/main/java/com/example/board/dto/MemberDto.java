package com.example.board.dto;

import com.example.board.entity.constant.MemberRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemberDto {

    @Email
    @NotBlank(message = "이메일은 필수요소입니다.")
    private String email;

    @NotBlank(message = "이름은 필수요소입니다.")
    private String name;

    @NotBlank(message = "비밀번호는 필수요소입니다.")
    private String password;

    private MemberRole role;
}

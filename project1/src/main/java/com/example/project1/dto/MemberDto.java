package com.example.project1.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemberDto {
    // @NotEmpty(message = "아이디는 필수 요소입니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[!@#])[A-Za-z0-9!@#]{6,12}$", message = "아이디는 영어 대소문자, 숫자, 특수문자(!@#)를 사용해서 6~12자리로 작성해야 합니다.")
    private String userid;
    @NotEmpty(message = "비밀번호는 필수 요소입니다.")
    private String password;
    @NotEmpty(message = "이름은 필수 요소입니다.")
    private String name;

    // @Min, @Max와 @NotEmpty, @NotBlank는 같이 사용 불가
    // @NotEmpty(message = "나이는 필수 요소입니다.")
    @NotNull(message = "나이는 필수 요소입니다.")
    @Min(value = 0, message = "최소 0 이상이어야 합니다.")
    @Max(value = 120, message = "최대 120 이하이어야 합니다.")
    private Integer age;

    @NotEmpty(message = "이메일은 필수 요소입니다.")
    @Email(message = "이메일 형식을 확인해 주세요.")
    private String email;
}

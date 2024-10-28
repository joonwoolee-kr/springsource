package com.example.project1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// validation(유효성 검증)
// 사용자 입력값 검증
// 1. front 단: javascript
// 2. back 단: spring 지원
// 주요 어노테이션
// 1) @NotEmpty: Null 체크, 문자열의 길이가 0인지 검사
// 2) @NotBlank: Null 체크, 문자열의 길이가 0인지 검사, 빈 문자열("") 검사
// 3) @Length(min=3,max=5): 3~5 길이 검사
// 4) @Email: 이메일 형식 검사
// 5) @Max(8) / @Min(3): 지정한 값보다 큰지 / 작은지 검사
// 6) @Null: 값이 null인지 검사
// 7) @NotNull: 값이 not null인지 검사
// 8) @Size(20): 문자의 길이
// 9) @Pattern(regexp="정규식"): 정규식 패턴에 맞는지 검사

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginDto {
    @NotEmpty
    private String userid;
    @NotBlank
    private String password;
}

package com.example.board.dto;

import java.time.LocalDateTime;

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
public class BoardDto {

    private Long bno;

    @NotBlank(message = "제목은 필수 입력 요소입니다.")
    private String title;

    @NotBlank(message = "내용은 필수 입력 요소입니다.")
    private String content;

    // private Member writer;
    @NotBlank(message = "작성자는 필수 입력 요소입니다.")
    @Email(message = "이메일 형식을 확인해주세요.")
    private String writerEmail;
    private String writerName;

    private LocalDateTime regDate;
    private LocalDateTime updateDate;

    // 댓글 수
    private Long replyCnt;
}

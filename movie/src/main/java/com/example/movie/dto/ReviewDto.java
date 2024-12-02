package com.example.movie.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ReviewDto {

    private Long reviewNo;

    private int grade;

    private String text;

    // Movie의 mno 담기
    private Long mno;

    // Member mid,nickname,email
    private Long mid;
    private String email;
    private String nickname;

    private LocalDateTime regDate;
    private LocalDateTime updateDate;
}

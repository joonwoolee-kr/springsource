package com.example.board.dto;

import java.time.LocalDateTime;

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
    private String title;
    private String content;

    // private Member writer;
    private String writerEmail;
    private String writerName;

    private LocalDateTime regDate;
    private LocalDateTime updateDate;

    // 댓글 수
    private Long replyCnt;
}

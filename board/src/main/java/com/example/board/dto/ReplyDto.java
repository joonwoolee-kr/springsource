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
public class ReplyDto {
    private Long rno;

    private String replyer;

    private String text;

    private Long bno; // 게시글 번호(부모)

    private LocalDateTime regDate;
    private LocalDateTime updateDate;
}

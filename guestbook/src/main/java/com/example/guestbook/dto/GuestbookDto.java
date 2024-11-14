package com.example.guestbook.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class GuestbookDto {

    private Long gno;

    @NotBlank(message = "Title은 필수 입력 요소입니다.")
    private String title;

    @NotBlank(message = "Content는 필수 입력 요소입니다.")
    private String content;

    @NotBlank(message = "Writer는 필수 입력 요소입니다.")
    private String writer;

    private LocalDateTime regDate;

    private LocalDateTime updateDate;

}

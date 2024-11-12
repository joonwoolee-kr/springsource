package com.example.memo.dto;

import java.time.LocalDateTime;

import org.springframework.data.annotation.LastModifiedDate;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemoDto {
    private Long mno;

    @NotBlank(message = "내용 입력")
    private String memoText;

    private LocalDateTime createdDateTime; // 최초 생성 시간
    private LocalDateTime lastModifiedDateTime; // 최종 수정 시간
}

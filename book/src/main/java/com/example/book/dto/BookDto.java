package com.example.book.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class BookDto {

    private Long id;

    @NotBlank(message = "도서명을 입력해주세요")
    private String title;

    @NotBlank(message = "저자를 입력해주세요")
    private String writer;

    @NotNull(message = "가격을 입력해주세요")
    private Integer price;

    @NotNull(message = "할인가격을 입력해주세요")
    private Integer salePrice;

    @NotBlank(message = "카테고리를 입력해주세요")
    private String categoryName;

    @NotBlank(message = "출판사를 입력해주세요")
    private String publisherName;

    private LocalDateTime createdDateTime;

    private LocalDateTime lastModifiedDateTime;
}

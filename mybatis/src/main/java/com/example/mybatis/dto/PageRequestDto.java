package com.example.mybatis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// http://localhost:8080/book/list?page=2&size=20&type=c&keyword=title
@Builder
@AllArgsConstructor
@ToString
@Setter
@Getter
public class PageRequestDto {
    private int page;
    private int size;

    // 검색
    private String type;
    private String keyword;

    public PageRequestDto() {
        this.page = 1;
        this.size = 10;
    }

    public String[] getTypeArr() {
        return type == null ? new String[] {} : type.split("");
    }

}

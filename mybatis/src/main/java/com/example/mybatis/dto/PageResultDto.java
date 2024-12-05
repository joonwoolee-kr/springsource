package com.example.mybatis.dto;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.Data;

// Page<Book> result 결과를 담는 Dto
// Entity => Dto: result.getContent() => List<BookDto> 변경

@Data
public class PageResultDto {

    // 총 개수
    private int total;
    private PageRequestDto requestDto;

    // 시작 페이지, 끝 페이지 번호
    private int start, end;

    // 총 페이지 수
    private int totalPage;

    // 이전, 다음 여부
    private boolean prev, next;

    // 화면에 보여줄 페이지 번호 목록
    private List<Integer> pageList;

    public PageResultDto(PageRequestDto requestDto, int total) {
        this.total = total;

        int tempEnd = (int) (Math.ceil(requestDto.getPage() / 10.0)) * requestDto.getSize();
        totalPage = (int) (Math.ceil((total / 1.0) / requestDto.getSize()));

        this.start = tempEnd - 9;
        this.prev = this.start > 1;
        this.end = totalPage > tempEnd ? tempEnd : totalPage;
        this.next = totalPage > tempEnd;

        pageList = IntStream.rangeClosed(start, end)
                .boxed() // int => Integer(리스트는 객체로만 담을 수 있기 때문에)
                .collect(Collectors.toList());
    }

}

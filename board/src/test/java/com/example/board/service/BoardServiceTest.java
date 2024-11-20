package com.example.board.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.board.dto.BoardDto;
import com.example.board.dto.PageRequestDto;
import com.example.board.dto.PageResultDto;

@SpringBootTest
public class BoardServiceTest {

    @Autowired
    private BoardService boardService;

    @Test
    public void testList() {

        PageRequestDto requestDto = new PageRequestDto();
        PageResultDto<BoardDto, Object[]> result = boardService.getList(requestDto);

        result.getDtoList().forEach(dto -> System.out.println(dto));
        System.out.println("전체 페이지 " + result.getTotalPage());
    }
}

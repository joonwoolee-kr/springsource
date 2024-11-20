package com.example.board.repository.search;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchBoardRepository {
    // 전체 리스트
    List<Object[]> list();

    // 페이지나누기 + 검색 리스트
    Page<Object[]> list(String type, String keyword, Pageable pageable);

    // 상세조회
    Object[] getBoardByBno(Long bno);

}

package com.example.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.board.entity.Board;
import com.example.board.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    // reply 테이블의 board_bno를 이용해 reply를 삭제하는 쿼리 메소드 작성
    @Modifying // update or delete의 경우 필수
    @Query("DELETE FROM Reply r WHERE r.board.bno = :bno")
    void deleteByBno(Long bno);

    // 특정 bno의 댓글 추출
    List<Reply> findByBoardOrderByRno(Board board);

}

package com.example.project2.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.project2.entity.Board;
import com.example.project2.entity.Item;
import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    // 1. spring data jpa 쿼리 메소드 방법 사용
    // where title = ?
    // List<Board> findByTitle(String title);

    // where title like ?
    // List<Board> findByTitleLike(String title);

    // where title like '%title'
    // List<Board> findByTitleStartingWith(String title);

    // where writer like 'writer%'
    // List<Board> findByWriterEndingWith(String writer);

    // where writer like '%writer%'
    // List<Board> findByWriterContaining(String writer);

    // where writer like '%writer%' or title like '%title%'
    // List<Board> findByWriterContainingOrTitleContaining(String writer, String
    // title);

    // where title like '%title%' and id > 10
    // List<Board> findByTitleContainingAndIdGreaterThan(String title, Long id);

    // where id > 10 order by id desc
    // List<Board> findByIdGreaterThanOrderByIdDesc(Long id);

    // List<Board> findByIdGreaterThanOrderByIdDesc(Long id, Pageable pageable);

    // 2. @Query 어노테이션 사용
    // 실 sql 은 아님
    // select * from board b where b.writer like '%user%' and b.id > 0 order by b.id
    // desc
    // @Query("SELECT b FROM Board b WHERE b.writer LIKE %:writer% AND b.id > 0
    // ORDER BY b.id DESC")
    @Query("SELECT b FROM Board b WHERE b.writer LIKE %?1% AND b.id > 0 ORDER BY b.id DESC")
    List<Board> findByWriterList(String writer);

    // @Query("SELECT b FROM Board b WHERE b.title = :title")
    // @Query("SELECT b FROM Board b WHERE b.title LIKE %:title%")
    @Query("SELECT b FROM Board b WHERE b.title LIKE %?1%")
    List<Board> findByTitle(String title);

    @Query("SELECT b FROM Board b WHERE b.writer LIKE %?1% OR b.title LIKE %?2%")
    List<Board> findByWriterContainingOrTitleContaining(String writer, String title);
}

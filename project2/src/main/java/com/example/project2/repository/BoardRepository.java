package com.example.project2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project2.entity.Board;
import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    // where title = ?
    List<Board> findByTitle(String title);

    // where title like ?
    List<Board> findByTitleLike(String title);

}

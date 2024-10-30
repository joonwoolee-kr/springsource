package com.example.project2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project2.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

}

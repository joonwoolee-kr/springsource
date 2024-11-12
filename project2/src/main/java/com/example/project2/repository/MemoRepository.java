package com.example.project2.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project2.entity.Memo;

public interface MemoRepository extends JpaRepository<Memo, Long> {
    // save(Entity) : insert, update
    // findById(pk) : pk 하나 조회
    // findAll() : 전체 조회
    // deleteById(pk) : 하나 삭제
    // delete(Entity) : 하나 삭제
}

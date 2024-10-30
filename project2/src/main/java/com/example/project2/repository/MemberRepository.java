package com.example.project2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project2.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String> {

}

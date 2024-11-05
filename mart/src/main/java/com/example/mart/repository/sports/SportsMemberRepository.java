package com.example.mart.repository.sports;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mart.entity.sports.SportsMember;

public interface SportsMemberRepository extends JpaRepository<SportsMember, Long> {

}

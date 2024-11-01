package com.example.project3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.project3.entity.Member;
import com.example.project3.entity.Team;

public interface MemberRepository extends JpaRepository<Member, String> {

    // from 절에 Entity 이름(대소문자 구별) 써야 함
    @Query("SELECT m FROM Member m JOIN m.team t WHERE t.name = :name")
    public List<Member> findByMemberEqualTeam(String name);

    List<Member> findByTeam(Team team);

}

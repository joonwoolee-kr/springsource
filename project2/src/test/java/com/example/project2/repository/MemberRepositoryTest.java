package com.example.project2.repository;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

import org.antlr.v4.runtime.misc.IntSet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.project2.entity.Member;
import com.example.project2.entity.constant.RoleType;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void insertTest() {
        // Member member = Member.builder()
        // .id("user1")
        // .username("user1")
        // .age(20)
        // .roleType(RoleType.USER)
        // .build();
        // memberRepository.save(member);

        IntStream.rangeClosed(2, 10)
                .forEach(i -> {
                    Member member = Member.builder()
                            .id("user" + i)
                            .username("user" + i)
                            .age(20 + i)
                            .roleType(RoleType.USER)
                            .createdDate(LocalDateTime.now())
                            .build();
                    memberRepository.save(member);
                });
    }

    @Test
    public void selectOneTest() {
        Member member = memberRepository.findById("user7").get();
        System.out.println(member);
    }

    @Test
    public void selectAllTest() {
        memberRepository.findAll().forEach(member -> System.out.println(member));
    }

    @Test
    public void updateTest() {
        // save() : insert or update
        // Member member = Member.builder()
        // .id("user8")
        // .username("user8")
        // .age(28)
        // .roleType(RoleType.ADMIN)
        // .lastModifiedDate(LocalDateTime.now())
        // .build();
        Member member = memberRepository.findById("user9").get();
        member.setRoleType(RoleType.ADMIN);
        member.setLastModifiedDate(LocalDateTime.now());
        memberRepository.save(member);
    }

    @Test
    public void deleteTest() {
        // memberRepository.delete(null);
        memberRepository.deleteById("user10");

    }
}

package com.example.mart.repository;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.mart.entity.sports.Locker;
import com.example.mart.entity.sports.SportsMember;
import com.example.mart.repository.sports.LockerRepository;
import com.example.mart.repository.sports.SportsMemberRepository;

@SpringBootTest
public class LockerRepositoryTest {
    @Autowired
    private SportsMemberRepository memberRepository;

    @Autowired
    private LockerRepository lockerRepository;

    @Test
    public void testLockerInsert() {
        IntStream.rangeClosed(5, 8).forEach(i -> {
            // locker 4
            Locker locker = Locker.builder()
                    .name("locker" + i)
                    .build();
            lockerRepository.save(locker);
        });

        LongStream.rangeClosed(5, 8).forEach(i -> {
            Locker locker = Locker.builder().id(i).build();
            // member 4
            SportsMember member = SportsMember.builder()
                    .name("user" + i)
                    .locker(locker)
                    .build();
            memberRepository.save(member);
        });
    }

    @Test
    public void testMemberUpdate() {
        SportsMember sportsMember = memberRepository.findById(5L).get();
        sportsMember.setName("test5");
        memberRepository.save(sportsMember);
    }

    @Transactional
    @Test
    public void testMemberRead() {
        // 회원 조회(+ Locker 조회)
        SportsMember sportsMember = memberRepository.findById(3L).get();
        System.out.println(sportsMember);

        // 객체 그래프 탐색
        System.out.println(sportsMember.getLocker());

        // 전체 회원 조회
        memberRepository.findAll().forEach(member -> {
            System.out.println(member);
            System.out.println(member.getLocker());
        });
    }

    @Test
    public void testLockerRead() {

        // 전체 회원 조회
        lockerRepository.findAll().forEach(locker -> {
            System.out.println(locker);
            System.out.println(locker.getSportsMember());
        });
    }
}

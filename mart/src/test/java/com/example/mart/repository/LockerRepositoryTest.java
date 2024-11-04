package com.example.mart.repository;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.mart.entity.sports.Locker;
import com.example.mart.entity.sports.Member;
import com.example.mart.repository.sports.LockerRepository;
import com.example.mart.repository.sports.MemberRepository;

@SpringBootTest
public class LockerRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private LockerRepository lockerRepository;

    @Test
    public void testLockerInsert() {
        IntStream.rangeClosed(1, 4).forEach(i -> {
            // locker 4
            Locker locker = Locker.builder()
                    .name("locker" + i)
                    .build();
            lockerRepository.save(locker);
        });

        LongStream.rangeClosed(1, 4).forEach(i -> {
            Locker locker = Locker.builder().id(i).build();

            // member 4
            Member member = Member.builder()
                    .name("user" + i)
                    .locker(locker)
                    .build();
            memberRepository.save(member);
        });
    }
}

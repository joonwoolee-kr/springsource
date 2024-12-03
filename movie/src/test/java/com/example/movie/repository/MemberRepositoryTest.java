package com.example.movie.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;

import com.example.movie.entity.Member;
import com.example.movie.entity.constant.MemberRole;

import jakarta.transaction.Transactional;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void testInsert() {

        IntStream.rangeClosed(1, 50).forEach(i -> {
            Member member = Member.builder()
                    .email("user" + i + "@gmail.com")
                    .password(passwordEncoder.encode("1111"))
                    .nickname("nickname" + i)
                    .role(MemberRole.MEMBER)
                    .build();

            memberRepository.save(member);
        });
    }

    @Test
    public void testUpdate() {

        Member member = memberRepository.findById(2L).get();
        member.setNickname("flower");
        memberRepository.save(member);
    }

    @Transactional
    @Test
    public void testUpdate2() {

        memberRepository.updateNickName("test", "user3@gmail.com");

    }

    @Commit
    @Transactional
    @Test
    public void testDelete() {
        reviewRepository.deleteByMember(Member.builder().mid(49L).build());
        memberRepository.deleteById(49L);
    }

}

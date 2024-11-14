package com.example.board.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.board.entity.Board;
import com.example.board.entity.Member;
import com.example.board.entity.Reply;

@SpringBootTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void testInsertMember() {
        // 30명
        IntStream.rangeClosed(1, 30).forEach(i -> {
            Member member = Member.builder()
                    .email("email" + i + "@gmail.com")
                    .name("user" + i)
                    .password("1111")
                    .build();
            memberRepository.save(member);
        });
    }

    @Test
    public void testInsertBoard() {
        // 100개
        IntStream.rangeClosed(1, 100).forEach(i -> {
            int num = (int) (Math.random() * 30) + 1;
            Board board = Board.builder()
                    .title("title" + i)
                    .content("content" + i)
                    // .writer(memberRepository.findById("email" + num + "@gmail.com").get())
                    .writer(Member.builder().email("email" + num + "@gmail.com").build())
                    .build();
            boardRepository.save(board);
        });
    }

    @Test
    public void testInsertReply() {
        // 100개
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Long num = (long) (Math.random() * 100) + 1;
            Reply reply = Reply.builder()
                    .replyer("replyer" + i)
                    .text("text" + i)
                    // .board(boardRepository.findById(num).get())
                    .board(Board.builder().bno(num).build())
                    .build();
            replyRepository.save(reply);
        });
    }
}

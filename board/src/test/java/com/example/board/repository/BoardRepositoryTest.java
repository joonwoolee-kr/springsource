package com.example.board.repository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import com.example.board.entity.Board;
import com.example.board.entity.Member;
import com.example.board.entity.Reply;

import jakarta.transaction.Transactional;

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

    @Transactional
    @Test
    public void testReadBoard() {
        // 100
        Board board = boardRepository.findById(100L).get();
        System.out.println(board);

        // 객체 그래프 탐색: Board, Member 관계
        System.out.println(board.getWriter());
    }

    @Transactional
    @Test
    public void testReadReply() {
        // 100
        Reply reply = replyRepository.findById(100L).get();
        System.out.println(reply);

        // 객체 그래프 탐색: Reply, Board 관계
        // 원본글
        System.out.println(reply.getBoard());
    }

    @Transactional
    @Test
    public void testReadBoardReply() {
        // 100
        Board board = boardRepository.findById(100L).get();
        System.out.println(board);

        System.out.println(board.getReplies());
    }

    @Test
    public void testJoin() {
        List<Object[]> result = boardRepository.list();
        for (Object[] objects : result) {
            System.out.println(Arrays.toString(objects));
            // Board board = (Board) objects[0];
            // Member member = (Member) objects[1];
            // Long replycnt = (Long) objects[2];
        }
    }

    @Test
    public void testJoinList() {
        // Pageable pageable = PageRequest.of(0, 10,
        // Sort.by("bno").descending().and(Sort.by("title").descending()));
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

        Page<Object[]> result = boardRepository.list("tc", "content", pageable);

        for (Object[] objects : result) {
            System.out.println(Arrays.toString(objects));
        }
    }

    @Test
    public void testJoinRow() {
        Object[] object = boardRepository.getBoardByBno(100L);

        // [Board(bno=100, title=title100, content=content100),
        // Member(email=email20@gmail.com, name=user20, password=1111), 1]
        System.out.println(Arrays.toString(object));
    }

    @Commit
    @Transactional
    @Test
    public void testReplyRemove() {
        replyRepository.deleteByBno(1L);
        boardRepository.deleteById(1L);
    }

    @Test
    public void testReplyRemove2() {
        // 부모 제거 시 자식(Reply) 함께 제거
        boardRepository.deleteById(3L);
    }

    @Test
    public void testReplyList() {
        Board board = Board.builder().bno(10L).build();
        List<Reply> list = replyRepository.findByBoardOrderByRno(board);

        list.forEach(b -> System.out.println(b));
    }
}

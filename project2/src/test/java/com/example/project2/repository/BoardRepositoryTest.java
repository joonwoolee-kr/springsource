package com.example.project2.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.example.project2.entity.Board;
import com.example.project2.entity.Memo;

@SpringBootTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    // C(insert)
    @Test
    public void insertTest() {

        IntStream.rangeClosed(1, 300).forEach(i -> {
            Board board = Board.builder()
                    .title("Title...." + i)
                    .content("Content...." + i)
                    .writer("user" + i)
                    .build();
            boardRepository.save(board);
        });
    }

    // R(Read)
    @Test
    public void selectOneTest() {
        System.out.println(boardRepository.findById(6L));
    }

    @Test
    public void selectAllTest() {
        boardRepository.findAll().forEach(board -> System.out.println(board));
    }

    // U
    @Test
    public void updateTest() {
        Board board = boardRepository.findById(5L).get();
        board.setTitle("Update Title");
        board.setContent("Update Content");
        boardRepository.save(board);
    }

    // D
    @Test
    public void deleteTest() {
        boardRepository.deleteById(15L);
    }

    // 쿼리 메소드
    @Test
    public void testTitleList() {
        // boardRepository.findByTitle("Title....").forEach(b -> System.out.println(b));
        // boardRepository.findByTitleLike("Title").forEach(b -> System.out.println(b));
        // boardRepository.findByTitleStartingWith("Title").forEach(b ->
        // System.out.println(b));

        // boardRepository.findByWriterEndingWith("1").forEach(b ->
        // System.out.println(b));
        // boardRepository.findByWriterContaining("user").forEach(b ->
        // System.out.println(b));
        boardRepository.findByWriterContainingOrTitleContaining("user", "Title")
                .forEach(b -> System.out.println(b));

        // boardRepository.findByTitleContainingAndIdGreaterThan("Title", 10L)
        // .forEach(b -> System.out.println(b));

        // boardRepository.findByIdGreaterThanOrderByIdDesc(0L)
        // .forEach(b -> System.out.println(b));

        // 0 : 1 page 의미, pageSize : 한 페이지에 보여질 게시물 개수
        // Pageable pageable = PageRequest.of(1, 10);

        // boardRepository.findByIdGreaterThanOrderByIdDesc(0L, pageable)
        // .forEach(b -> System.out.println(b));

        // boardRepository
        // .findByWriterList("user")
        // .forEach(b -> System.out.println(b));

    }
}

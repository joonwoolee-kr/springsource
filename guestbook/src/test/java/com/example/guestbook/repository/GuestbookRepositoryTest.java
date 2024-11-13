package com.example.guestbook.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.guestbook.entity.Guestbook;
import com.example.guestbook.entity.QGuestbook;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

@SpringBootTest
public class GuestbookRepositoryTest {

    @Autowired
    private GuestbookRepository guestbookRepository;

    @Test
    public void insertTest() {
        IntStream.rangeClosed(1, 300).forEach(i -> {
            Guestbook guestbook = Guestbook.builder()
                    .title("title " + i)
                    .content("content " + i)
                    .writer("writer " + i)
                    .build();
            guestbookRepository.save(guestbook);
        });
    }

    @Test
    public void updateTest() {
        // 300번 title, content 수정
        Guestbook guestbook = guestbookRepository.findById(300L).get();
        guestbook.setTitle("title 300 update");
        guestbook.setContent("content 300 update");
        guestbookRepository.save(guestbook);
    }

    @Test
    public void searchTest() {
        // 제목에 1이 포함된 게시물 조회
        QGuestbook qGuestbook = QGuestbook.guestbook; // == new ~

        Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qGuestbook.title.contains("1"));

        Page<Guestbook> result = guestbookRepository.findAll(builder, pageable);
        result.stream().forEach(guestbook -> System.out.println(guestbook));
    }

    @Test
    public void searchTest2() {
        // 제목 or 내용에 1이 포함되어 있고, gno > 0 인 게시물 조회
        QGuestbook qGuestbook = QGuestbook.guestbook; // == new ~

        Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());

        String keyword = "1";

        BooleanBuilder builder = new BooleanBuilder();
        BooleanExpression expressionTitle = qGuestbook.title.contains(keyword);
        BooleanExpression expressionContent = qGuestbook.content.contains(keyword);
        builder.and(expressionTitle.or(expressionContent));
        builder.and(qGuestbook.gno.gt(0L));

        Page<Guestbook> result = guestbookRepository.findAll(builder, pageable);
        result.stream().forEach(guestbook -> System.out.println(guestbook));
    }

    @Test
    public void searchTest3() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());

        Page<Guestbook> result = guestbookRepository.findAll(guestbookRepository.makePredicate("tc", "title"),
                pageable);
        result.stream().forEach(guestbook -> System.out.println(guestbook));
    }

    @Test
    public void deleteTest() {
        guestbookRepository.deleteById(250L);
    }
}

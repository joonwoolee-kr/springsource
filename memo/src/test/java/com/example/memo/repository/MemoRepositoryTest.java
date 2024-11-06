package com.example.memo.repository;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.memo.entity.Memo;

@SpringBootTest
public class MemoRepositoryTest {

    @Autowired
    private MemoRepository memoRepository;

    @Test
    public void testMemoInsert() {
        IntStream.rangeClosed(1, 10).forEach(i -> {
            Memo memo = Memo.builder()
                    .memoText("memo" + i)
                    .build();
            memoRepository.save(memo);
        });
    }

    @Test
    public void testMemoRead() {
        // 6번 메모 가져오기
        Memo memo = memoRepository.findById(6L).get();
        System.out.println(memo);

        // 전체 메모 가져오기
        List<Memo> list = memoRepository.findAll();
        list.forEach(m -> System.out.println(m));
    }

    @Test
    public void testMemoUpdate() {
        // 7번 메모 내용 수정
        Memo memo = memoRepository.findById(7L).get();
        memo.setMemoText("memo update");
        memoRepository.save(memo);
    }

    @Test
    public void testMemoDelete() {
        // 메모 삭제
        memoRepository.deleteById(10L);
    }
}

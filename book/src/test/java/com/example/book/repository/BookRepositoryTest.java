package com.example.book.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.book.entity.Book;
import com.example.book.entity.Category;
import com.example.book.entity.Publisher;

@SpringBootTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Test
    public void testCategoryAndPublisherList() {
        // category 목록
        categoryRepository.findAll().forEach(category -> System.out.println(category.getName()));
        // publisher 목록
        publisherRepository.findAll().forEach(publisher -> System.out.println(publisher.getName()));
    }

    @Test
    public void testCategoryInsert() {
        // 소설, 건강, 컴퓨터, 여행, 경제
        categoryRepository.save(Category.builder().name("소설").build());
        categoryRepository.save(Category.builder().name("건강").build());
        categoryRepository.save(Category.builder().name("컴퓨터").build());
        categoryRepository.save(Category.builder().name("여행").build());
        categoryRepository.save(Category.builder().name("경제").build());
    }

    @Test
    public void testPublisherInsert() {
        // 미래의창, 웅진리빙하우스, 김영사, 길벗, 문학과지성사
        publisherRepository.save(Publisher.builder().name("미래의창").build());
        publisherRepository.save(Publisher.builder().name("웅진리빙하우스").build());
        publisherRepository.save(Publisher.builder().name("김영사").build());
        publisherRepository.save(Publisher.builder().name("길벗").build());
        publisherRepository.save(Publisher.builder().name("문학과지성사").build());
    }

    @Test
    public void testBookInsert() {
        // 10권
        IntStream.rangeClosed(1, 10).forEach(i -> {
            // 무작위 publisher, category 지정에 사용
            long num1 = (int) (Math.random() * 5) + 1;
            long num2 = (int) (Math.random() * 5) + 1;
            int num3 = (int) ((Math.random() * 10) + 1) * 2000 + 10000;

            Book book = Book.builder()
                    .title("Book title " + i)
                    .writer("Writer " + i)
                    .price(num3)
                    .sale_price((int) (num3 * 0.8))
                    .category(Category.builder().id(num1).build())
                    .publisher(Publisher.builder().id(num2).build())
                    .build();
            bookRepository.save(book);
        });
    }

    @Transactional
    @Test
    public void testList() {
        // 도서 목록 전체 조회
        bookRepository.findAll().forEach(book -> {
            System.out.println(book);
            // category 정보
            System.out.println(book.getCategory());
            System.out.println(book.getPublisher());
        });
    }

    @Transactional
    @Test
    public void testGet() {
        // 특정 도서 조회
        Book book = bookRepository.findById(5L).get();
        System.out.println(book);
        System.out.println(book.getCategory().getName());
        System.out.println(book.getPublisher().getName());
    }

    @Test
    public void testUpdate() {
        // 특정 도서 수정
        Book book = bookRepository.findById(5L).get();
        book.setPrice(32000);
        book.setSale_price((int) (32000 * 0.8));
        bookRepository.save(book);
    }

    @Test
    public void testDelete() {
        // 특정 도서 삭제
        bookRepository.deleteById(10L);
    }
}

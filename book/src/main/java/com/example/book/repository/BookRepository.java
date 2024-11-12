package com.example.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.example.book.entity.Book;
import com.example.book.entity.QBook;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public interface BookRepository extends JpaRepository<Book, Long>, QuerydslPredicateExecutor<Book> {

    default Predicate makePredicate(String type, String keyword) {
        BooleanBuilder builder = new BooleanBuilder();
        QBook qBook = QBook.book;

        // id > 0: range scan
        builder.and(qBook.id.gt(0L));

        if (type == null)
            return builder;

        // 카테고리
        if (type.equals("c")) {
            builder.and(qBook.category.name.contains(keyword));
        }
        // 제목
        else if (type.equals("t")) {
            builder.and(qBook.title.contains(keyword));
        }
        // 저자
        else if (type.equals("w")) {
            builder.and(qBook.writer.contains(keyword));
        }

        return builder;
    }

}

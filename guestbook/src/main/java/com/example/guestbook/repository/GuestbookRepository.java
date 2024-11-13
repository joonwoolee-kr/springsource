package com.example.guestbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.example.guestbook.entity.Guestbook;
import com.example.guestbook.entity.QGuestbook;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public interface GuestbookRepository extends JpaRepository<Guestbook, Long>, QuerydslPredicateExecutor<Guestbook> {

    default Predicate makePredicate(String type, String keyword) {
        BooleanBuilder builder = new BooleanBuilder();
        QGuestbook qGuestbook = QGuestbook.guestbook;

        // id > 0: range scan
        builder.and(qGuestbook.gno.gt(0L));

        if (type == null)
            return builder;

        BooleanBuilder conditionBuilder = new BooleanBuilder();

        // 카테고리
        if (type.contains("c")) { // 내용
            conditionBuilder.or(qGuestbook.content.contains(keyword));
        }
        // 제목
        if (type.contains("t")) { // 제목
            conditionBuilder.or(qGuestbook.title.contains(keyword));
        }
        // 저자
        if (type.contains("w")) { // 작성자
            conditionBuilder.or(qGuestbook.writer.contains(keyword));
        }

        builder.and(conditionBuilder);

        return builder;
    }

}

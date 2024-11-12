package com.example.mart.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.example.mart.entity.item.Item;
import com.example.mart.entity.item.Member;
import com.example.mart.entity.item.Order;
import com.example.mart.entity.item.QItem;
import com.example.mart.entity.item.QMember;
import com.example.mart.entity.item.QOrder;
import com.example.mart.entity.item.QOrderItem;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;

public class QueryDslOrderRepositoryImpl extends QuerydslRepositorySupport implements QueryDslOrderRepository {

    public QueryDslOrderRepositoryImpl() {
        super(Order.class);
    }

    @Override
    public List<Member> members() {
        // select * from member where name='user1' order by name desc
        QMember qMember = QMember.member;
        JPQLQuery<Member> query = from(qMember);
        query.where(qMember.name.eq("user1")).orderBy(qMember.name.desc());
        JPQLQuery<Member> tuple = query.select(qMember);
        System.out.println(tuple);

        return tuple.fetch();
    }

    @Override
    public List<Item> items() {
        // select * from item where name='아파트' and price > 10000
        QItem item = QItem.item;
        JPQLQuery<Item> tuple = from(item)
                .where(item.name.eq("아파트").and(item.price.gt(10000)))
                .select(item);
        System.out.println("tuple" + tuple);
        return tuple.fetch();
    }

    @Override
    public List<Object[]> joinTest() {
        // select * from mart_order mo join mart_member mm on mo.member_member_id =
        // mm.member_id
        QMember qMember = QMember.member;
        QOrder qOrder = QOrder.order;
        QOrderItem qOrderItem = QOrderItem.orderItem;

        // JPQLQuery<Tuple> tuple =
        // from(qOrder).join(qMember).on(qOrder.member.eq(qMember)).select(qOrder,
        // qMember);

        // SELECT * FROM MART_ORDERS mo
        // JOIN MART_MEMBER mm ON mo.MEMBER_MEMBER_ID = mm.MEMBER_ID
        // LEFT JOIN MART_ORDER_ITEM moi ON mo.ORDER_ID = moi.ORDER_ORDER_ID;

        JPQLQuery<Tuple> tuple = from(qOrder)
                .join(qMember).on(qOrder.member.eq(qMember))
                .leftJoin(qOrderItem).on(qOrder.eq(qOrderItem.order))
                .select(qOrder, qMember, qOrderItem);

        List<Tuple> result = tuple.fetch();

        List<Object[]> list = result.stream().map(t -> t.toArray()).collect(Collectors.toList());

        return list;
    }

    @Override
    public List<Object[]> subQueryTest() {

        QMember qMember = QMember.member;
        QOrder qOrder = QOrder.order;
        QOrderItem qOrderItem = QOrderItem.orderItem;

        // select에 사용할 서브쿼리 생성
        JPQLQuery<Long> orderCnt = JPAExpressions.select(qOrderItem.order.count().as("order_cnt"))
                .from(qOrderItem)
                .where(qOrderItem.order.eq(qOrder))
                .groupBy(qOrderItem.order);

        JPQLQuery<Tuple> tuple = from(qOrder)
                .join(qMember).on(qOrder.member.eq(qMember))
                .select(qOrder, qMember, orderCnt);

        List<Tuple> result = tuple.fetch();

        List<Object[]> list = result.stream().map(t -> t.toArray()).collect(Collectors.toList());

        return list;
    }
}

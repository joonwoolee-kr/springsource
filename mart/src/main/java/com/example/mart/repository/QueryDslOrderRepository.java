package com.example.mart.repository;

import java.util.List;

import com.example.mart.entity.item.Item;
import com.example.mart.entity.item.Member;

public interface QueryDslOrderRepository {

    List<Member> members();

    List<Item> items();

    List<Object[]> joinTest();

}

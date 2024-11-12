package com.example.mart.repository.item;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mart.entity.item.Order;
import com.example.mart.repository.QueryDslOrderRepository;

public interface OrderRepository extends JpaRepository<Order, Long>, QueryDslOrderRepository {

}

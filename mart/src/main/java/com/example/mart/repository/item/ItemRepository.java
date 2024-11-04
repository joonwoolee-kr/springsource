package com.example.mart.repository.item;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mart.entity.item.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

}

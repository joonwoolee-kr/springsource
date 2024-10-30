package com.example.project2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project2.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

}

package com.example.project2.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.example.project2.entity.constant.ItemStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 아이템명(item_nm), 가격(price), 재고수량(stock_number), 상세설명(item_detail),
// 판매상태(item_sell_status) - SELL, SOLD_OUT, 등록시간(reg_time), 수정시간(update_time)

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@SequenceGenerator(name = "item_seq_gen", sequenceName = "item_seq", allocationSize = 1)
@Entity
public class Item {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_seq_gen")
    @Id
    private Long id;

    @Column(nullable = false, length = 100)
    private String itemNm; // item_nm

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int stockNumber;

    private String itemDetail;

    @Enumerated(EnumType.STRING)
    private ItemStatus itemSellStatus;

    @CreatedDate
    private LocalDateTime regTime;

    @LastModifiedDate
    private LocalDateTime updateTime;

}

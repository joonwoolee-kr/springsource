package com.example.mart.entity.item;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
@SequenceGenerator(name = "mart_item_seq_gen", sequenceName = "mart_item_seq", allocationSize = 1)
@Table(name = "mart_item")
@Entity
public class Item extends BaseEntity {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mart_item_seq_gen")
    @Column(name = "item_id")
    @Id
    private Long id;

    private String name;

    private int price;

    private int quantity;
}

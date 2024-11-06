package com.example.mart.entity.product;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
@Getter
@Setter
@Entity
public abstract class Product {

    @SequenceGenerator(name = "product_seq_gen", sequenceName = "product_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq_gen")
    @Id
    @Column(name = "product_id")
    private Long id;

    private String name;

    private int price;
}

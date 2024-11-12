package com.example.project2.entity.jpql;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = { "member", "product" })
@Data
@SequenceGenerator(name = "jpql_order_seq_gen", sequenceName = "jpql_order_seq", allocationSize = 1)
@Table(name = "jpql_order")
@Entity
public class Order {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jpql_order_seq_gen")
    @Column(name = "order_id")
    @Id
    private Long id;

    private int orderAmount;

    @Embedded
    private Address address;

    // 주문을 누가 했느냐?
    @ManyToOne(fetch = FetchType.LAZY)
    private JpqlMember member;

    // 어떤 상품인가?
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
}

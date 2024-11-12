package com.example.mart.entity.item;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;

import com.example.mart.entity.constant.OrderStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@ToString(exclude = { "member", "orderItemsList", "delivery" })
@Setter
@Getter
@SequenceGenerator(name = "mart_order_seq_gen", sequenceName = "mart_order_seq", allocationSize = 1)
@Table(name = "mart_orders")
@Entity
public class Order extends BaseEntity {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mart_order_seq_gen")
    @Column(name = "order_id")
    @Id
    private Long id;

    @CreatedDate
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING) // 이 구문이 없다면 테이블에 입력될 때 0, 1 입력 됨
    private OrderStatus status;

    @ManyToOne
    private Member member;

    @OneToOne
    private Delivery delivery;

    // OrderItem ==> Order 접근하는 관계는 OrderItem 쪽에 설정
    // 왜? 외래키 있는 쪽에 관계 설정해야 함

    // Order ==> OrderItem 접근하기
    // order.getOrderItem()
    // , fetch = FetchType.EAGER
    @Builder.Default
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItemsList = new ArrayList<>();
}

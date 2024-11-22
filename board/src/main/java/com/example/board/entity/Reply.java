package com.example.board.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString(exclude = { "board" })
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reply extends BaseEntity {

    @Id
    @SequenceGenerator(name = "reply_seq_gen", sequenceName = "reply_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reply_seq_gen")
    private Long rno;

    @Column(nullable = false)
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member replyer;

    // fetch=>EAGER
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

}

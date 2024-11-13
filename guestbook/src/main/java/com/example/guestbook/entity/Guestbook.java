package com.example.guestbook.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
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
@Entity
public class Guestbook extends BaseEntity {

    @SequenceGenerator(name = "guestbook_seq_gen", sequenceName = "guestbook_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "guestbook_seq_gen")
    @Id
    private Long gno;

    @Column(nullable = false, length = 150)
    private String title;

    @Column(nullable = false, length = 1500)
    private String content;

    @Column(nullable = false, length = 50)
    private String writer;

}

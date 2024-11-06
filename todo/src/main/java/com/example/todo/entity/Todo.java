package com.example.todo.entity;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@SequenceGenerator(name = "todo_seq_gen", sequenceName = "todo_seq", allocationSize = 1)
@DynamicInsert // default 값 동작
@Entity(name = "todotbl")
public class Todo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "todo_seq_gen")
    @Column(name = "todo_id")
    private Long id;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Boolean completed;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Boolean important;

    private String title;
}

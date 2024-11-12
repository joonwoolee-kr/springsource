package com.example.project2.entity.jpql;

import jakarta.persistence.Column;
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
@Data
@ToString(exclude = "team")
@SequenceGenerator(name = "jpql_member_seq_gen", sequenceName = "jpql_member_seq", allocationSize = 1)
@Table(name = "jpql_member")
@Entity
public class JpqlMember {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jpql_member_seq_gen")
    @Column(name = "member_id")
    @Id
    private Long id;

    private int age;

    @Column(name = "user_name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Team team;

}

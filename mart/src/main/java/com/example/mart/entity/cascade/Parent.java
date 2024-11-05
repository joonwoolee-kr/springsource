package com.example.mart.entity.cascade;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@ToString(exclude = "childs")
@Setter
@Getter
@Entity
public class Parent {

    @SequenceGenerator(name = "parent_seq_gen", sequenceName = "parent_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parent_seq_gen")
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    // 양방향
    // CascadeType.PERSIST: 영속 상태 전이
    // CascadeType.MERGE: 병합
    // CascadeType.REMOVE: 삭제
    // CascadeType.ALL: 전체
    // CascadeType.REFRESH
    // CascadeType.DETACH

    // orphanRemoval = true: 부모 entity에서 자식 entity의 참조 제거 => 자식 entity의 부모가 제거된 상태,
    // 고아가 됨 => 고아가 된 entity 자동 삭제
    @Builder.Default
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Child> childs = new ArrayList<>();

}
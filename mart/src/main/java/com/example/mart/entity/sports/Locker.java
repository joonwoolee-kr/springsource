package com.example.mart.entity.sports;

import com.example.mart.entity.item.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@ToString(exclude = "sportsMember")
@Setter
@Getter
@SequenceGenerator(name = "sports_locker_seq_gen", sequenceName = "sports_locker_seq", allocationSize = 1)
@Table(name = "sports_locker")
@Entity
public class Locker extends BaseEntity {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sports_locker_seq_gen")
    @Column(name = "locker_id")
    @Id
    private Long id;

    private String name;

    // 양방향
    @OneToOne(mappedBy = "locker")
    private SportsMember sportsMember;
}

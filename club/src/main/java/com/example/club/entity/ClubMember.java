package com.example.club.entity;

import java.util.HashSet;
import java.util.Set;

import com.example.club.entity.constant.ClubRole;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ClubMember extends BaseEntity {

    @Id
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private boolean fromSocial;

    @Builder.Default
    @ElementCollection(fetch = FetchType.LAZY)
    private Set<ClubRole> roles = new HashSet<>();

    public void addMemberRole(ClubRole clubRole) {
        roles.add(clubRole);
    }

}

// @ElementCollection: 개념상 1:N 관계, 엔티티의 기본키를 PK 겸 FK로 사용, 부모 삭제 시 자식도 삭제
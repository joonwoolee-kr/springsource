package com.example.project2.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.example.project2.entity.constant.RoleType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 회원 테이블
// id, username, age
// 회원가입일, 수정일이 필요, 
// 회원 - 관리자,회원로 구분됨
// 회원 이름은 필수로 입력 / 10자 내로 입력
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "membertbl")
public class Member {

    @Id
    private String id;

    @Column(name = "name", nullable = false, length = 30)
    private String username;

    private int age;

    private RoleType roleType;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

}

package com.example.project3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project3.entity.Team;

public interface TeamRepository extends JpaRepository<Team, String> {

}

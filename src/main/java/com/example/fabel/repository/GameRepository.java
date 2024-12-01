package com.example.fabel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fabel.model.Games;

public interface GameRepository extends JpaRepository<Games, Long> {

}
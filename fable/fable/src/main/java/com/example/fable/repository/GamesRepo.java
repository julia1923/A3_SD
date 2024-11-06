package com.example.fable.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fable.model.Games;

public interface GamesRepo extends JpaRepository <Games, Integer> {

}

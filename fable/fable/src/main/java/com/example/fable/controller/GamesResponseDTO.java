package com.example.fable.controller;

import com.example.fable.model.Games;

public record GamesResponseDTO(Integer id, String name, Double price) {
    public GamesResponseDTO(Games games) {
        this(games.getId(), games.getName(), games.getPrice());
    }

}

package com.example.fabel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fabel.model.Games;
import com.example.fabel.repository.GameRepository;


@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    GameRepository gameRepository;

    @GetMapping("/all")
    public List<Games> getAllGames(){
        return gameRepository.findAll();
    }
    
    @PostMapping("/addNewGame")
    public Games addNewGame(@RequestBody Games game){

        Games addNewGame = new Games();
        addNewGame.setName(game.getName());
        addNewGame.setPrice(game.getPrice());
        addNewGame.setImage(game.getImage());

        return gameRepository.save(addNewGame);
    }

    @PutMapping("/updateGame")
    public Games updateGame(@RequestBody Games game){
        Games getGame = gameRepository.findById(game.getId()).orElseThrow();

        Games updateGame = new Games();
        updateGame.setName(getGame.getName());
        updateGame.setPrice(getGame.getPrice());
        updateGame.setImage(getGame.getImage());

        return gameRepository.save(updateGame);
    }
    
    @DeleteMapping ("/deleteGame/{id}")
    public Games deleteGame(@PathVariable Long id){
        Games getGame = gameRepository.findById(id).orElseThrow();
        gameRepository.delete(getGame);

        return getGame;
    }
}

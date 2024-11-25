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
        System.out.println("\n\n\n\n\n\n\n\nsucesso" + game.getName());

        Games getGame = gameRepository.findById(game.getId()).orElseThrow();

        System.out.println("\n\n\n\n\n\n\n\nsucesso2");
        Games updateGame = new Games();
        updateGame.setName(getGame.getName());
        updateGame.setPrice(getGame.getPrice());
        updateGame.setImage(getGame.getImage());

        System.out.println("\n\n\n\n\n\n\n\nsucesso3");

        Games jogo = gameRepository.save(updateGame); 
        System.out.println("qlqrcoisa");

        return jogo;
    }
    
    @DeleteMapping ("/deleteGame/{id}")
    public Games deleteGame(@PathVariable Long id){
        Games getGame = gameRepository.findById(id).orElseThrow();
        gameRepository.delete(getGame);

        return getGame;
    }
}

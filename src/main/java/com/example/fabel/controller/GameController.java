package com.example.fabel.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

        getGame.setName(game.getName());
        getGame.setPrice(game.getPrice());
        getGame.setImage(game.getImage());

        Games jogo = gameRepository.save(getGame); 

        return jogo;
    }
    
    @DeleteMapping ("/deleteGame/{id}")
    public Games deleteGame(@PathVariable Long id){
        Games getGame = gameRepository.findById(id).orElseThrow();
        gameRepository.delete(getGame);
        
        System.out.println("\n\n\n\n\n\n\n\n\n\n" + getGame);

        return getGame;
    }

    @PutMapping(value = "/updateImage", consumes = { "multipart/form-data" })
    public ResponseEntity<?> updateImage(@RequestParam("id") String idString, @RequestParam("avatar") MultipartFile avatar) throws IOException {

        Long id = Long.parseLong(idString);
        Games getGame = gameRepository.findById(id).orElseThrow();

        String avatarBase64 = Base64.getEncoder().encodeToString(avatar.getBytes());

        getGame.setImage(avatarBase64);
    
        if (avatar.getSize() > 5 * 1024 * 1024) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body("Avatar file is too large. Maximum size is 5MB.");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(gameRepository.save(getGame));
    }
}

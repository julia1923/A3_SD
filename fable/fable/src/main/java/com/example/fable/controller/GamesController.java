package com.example.fable.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fable.model.Games;
import com.example.fable.service.GamesService;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/games")

public class GamesController {

    @Autowired
    private GamesService service;

    // método para publicar um novo jogo - C
    @PostMapping
    public ResponseEntity<Games> createProduct(@RequestBody Games game) {
        Optional<Games> newGame = service.insert(game);

        if (newGame.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(newGame.get());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(null); // Retorna BAD REQUEST se o ID não deve ser fornecido
        }
    }

    //método para atualizar um jogo - U
    @PutMapping
    public ResponseEntity<Games> update(@RequestBody Games game) {
        Optional<Games> gameUpdated = service.update(game);

        if (gameUpdated.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<Games>(gameUpdated.get(), HttpStatus.OK);
    }

    //método para deletar um jogo pelo id - D
    @DeleteMapping("/{id}")
    public ResponseEntity<Games> deleteGame(@PathVariable int id){

        boolean deleted = service.delete(id);

        if(deleted){ //se "deleted" é verdadeiro.
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.notFound().build();
    }

    //método para retornar TODOS os itens da tablea do DB
    public ResponseEntity<List<Games>> getAll() {
        List<Games> allGames = service.findAll();

        if(allGames.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(allGames);
    }
}
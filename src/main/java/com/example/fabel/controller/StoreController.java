package com.example.fabel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fabel.model.Games;
import com.example.fabel.model.Store;
import com.example.fabel.model.Users;
import com.example.fabel.repository.GameRepository;
import com.example.fabel.repository.StoreRepository;
import com.example.fabel.repository.UserRepository;


@RestController
@RequestMapping("/store")
public class StoreController {

    @Autowired
    StoreRepository storeRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping
    public List<Store> getAllStore(){
        return storeRepository.findAll();
    }
    
    @PostMapping
    public Store addNewStore(@RequestBody Store store){

        Users getUser = userRepository.findById(store).orElseThrow();
        
        Store addNewStore = new Store();
        addNewStore.setUser(null);

        return userRepository.save(addNewGame);
    }

    @PutMapping
    public Games updateGame(@RequestBody Games game){
        Games getGame = gameRepository.findById(game.getId()).orElseThrow();

        Games updateGame = new Games();
        updateGame.setName(getGame.getName());
        updateGame.setPrice(getGame.getPrice());
        updateGame.setImage(getGame.getImage());

        return gameRepository.save(updateGame);
    }
    
    @DeleteMapping
    public Games deleteGame(@PathVariable Long id){
        Games getGame = gameRepository.findById(id).orElseThrow();
        gameRepository.delete(getGame);

        return getGame;
    }
}

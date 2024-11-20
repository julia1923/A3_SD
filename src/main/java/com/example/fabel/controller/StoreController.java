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
    @Autowired
    GameRepository gameRepository;

    @GetMapping("/all")
    public List<Store> getAllStore(){
        return storeRepository.findAll();
    }
    
    @PostMapping("/addNewStore")
    public Store addNewStore(@RequestBody Store store){

        Users getUser = userRepository.findById(store.getUser().getId()).orElseThrow();
        Games getGame = gameRepository.findById(store.getGame().getId()).orElseThrow();


        Store addNewStore = new Store();
        addNewStore.setUser(getUser);

        addNewStore.setGame(getGame);

        return storeRepository.save(addNewStore);
    }

    @PutMapping("/updateStore")
    public Store updateStore(@RequestBody Store store){

        Users getUser = userRepository.findById(store.getUser().getId()).orElseThrow();
        Games getGame = gameRepository.findById(store.getGame().getId()).orElseThrow();

        Store updateStore = new Store();
        updateStore.setUser(getUser);
        updateStore.setGame(getGame);

        return storeRepository.save(updateStore);
    }
    

    @DeleteMapping("/deleteStore/{id}")
    public Store deleteStore(@PathVariable Long id){
        Store getStore = storeRepository.findById(id).orElseThrow();
        storeRepository.delete(getStore);

        return getStore;
    }
}

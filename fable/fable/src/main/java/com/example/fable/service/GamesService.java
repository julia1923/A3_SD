package com.example.fable.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fable.model.Games;
import com.example.fable.repository.GamesRepo;

@Service
public class GamesService {

    @Autowired
    private GamesRepo repository;

    // verifica se o usuário digitou um id
    public Optional<Games> insert(Games game) {
        // Verifica se o ID foi fornecido
        if (game.getId() != null) {
            return Optional.empty(); // Retorna vazio se o ID estiver definido
        }
        return Optional.of(repository.save(game)); // Salva o produto e retorna o objeto salvo
    }

    // verifica a existencia do ID para atualizar
    public Optional<Games> update(Games games) {

        Optional<Games> idSearch = repository.findById(games.getId());

        if (idSearch.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(repository.save(games));
    }

    // verifica a existencia do ID para deletar 

    public boolean delete(int cod) {

        Optional<Games> gameToDelete = repository.findById(cod);

        if (gameToDelete.isEmpty()) {
            return false;

        }
        repository.deleteById(cod);
        return true;
    }

    //puxa todos os itens da tabela do DB
    public List<Games> findAll(){
        List<Games> allGames = repository.findAll(); 
        return allGames;
    }

}

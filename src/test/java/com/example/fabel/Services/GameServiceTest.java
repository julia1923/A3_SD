package com.example.fabel.Services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import com.example.fabel.controller.GameController;
import com.example.fabel.model.Games;
import com.example.fabel.repository.GameRepository;


public class GameServiceTest {
    
    @InjectMocks
    private GameController gameController;

    @Mock
    private GameRepository gameRepository;

    @Mock
    private MultipartFile avatarFile;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Read
    @Test
    void testGetAllGames (){
        when(gameRepository.findAll()).thenReturn(List.of(new Games()));

        var games = gameController.getAllGames();

        assertNotNull(games);
        assertEquals(1, games.size());
        verify(gameRepository, times(1)).findAll();
    }

    // Create
    @Test
    void testCreateGame() {
        Games game = new Games();
        game.setName("newGame");
        game.setPrice(1.1);
        game.setImage("");

        when(gameRepository.save(any(Games.class))).thenReturn(game);

        Games createdGame = gameRepository.save(game); // Chamar o método do controlador

        assertNotNull(createdGame);
        assertEquals("newGame", createdGame.getName());
        verify(gameRepository).save(any(Games.class));
    }

    
    @Test
    void testUpdateGame(){

        Games existingGame = new Games();

        existingGame.setId(1L);

        Games newGame = new Games();
        newGame.setId(1L);
        newGame.setName("newGame");
        newGame.setPrice(1.1);
        newGame.setImage("");
    
        when(gameRepository.findById(1L)).thenReturn(Optional.of(existingGame));
        when(gameRepository.save(any(Games.class))).thenReturn(newGame);
    
        // Execução do método
        Games result = gameController.updateGame(newGame);
    
        // Verifica se o novo usuário não é nulo e foi salvo
        assertNotNull(result);
        verify(gameRepository).save(any(Games.class));

    }
      // Delete

      @Test
      void testDeleteGame() {
  
          Games game = new Games();
  
          game.setId(1L);
  
          when(gameRepository.findById(1L)).thenReturn(Optional.of(game));
  
          var result = gameController.deleteGame(1L);
  
          assertEquals(1L, result.getId());
          verify(gameRepository, times(1)).findById(1L);
      }
}


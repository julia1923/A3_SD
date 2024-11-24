package com.example.fabel.Services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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







    // Update





    // DELETE
}

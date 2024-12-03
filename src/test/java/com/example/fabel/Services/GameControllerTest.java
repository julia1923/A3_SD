package com.example.fabel.Services;

import static org.junit.jupiter.api.Assertions.*;

import org.springframework.http.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.example.fabel.model.Games;
import com.example.fabel.repository.GameRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private GameRepository gameRepository;

    private Games game;

    @BeforeEach
    public void setup() {
        
        game = new Games();
        game.setName("Test Game");
        game.setPrice(59.99);
        game.setImage("game_image_url");
        game = gameRepository.save(game);
    }

    @Test
    public void testGetAllGames() throws Exception {
        mockMvc.perform(get("/games/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddNewGame() throws Exception {
        
        Games newGame = new Games();
        newGame.setName("New Game");
        newGame.setPrice(49.99);
        newGame.setImage("new_game_image_url");

        String gameJson = objectMapper.writeValueAsString(newGame);

        mockMvc.perform(post("/games/addNewGame")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gameJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("New Game"))
                .andExpect(jsonPath("$.price").value(49.99))
                .andExpect(jsonPath("$.image").value("new_game_image_url"));
    }

    @Test
    public void testUpdateGame() throws Exception {
        
        game.setName("Updated Game");
        game.setPrice(69.99);
        game.setImage("updated_game_image_url");

        String gameJson = objectMapper.writeValueAsString(game);

        mockMvc.perform(put("/games/updateGame")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gameJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(game.getName()))
                .andExpect(jsonPath("$.price").value(69.99))
                .andExpect(jsonPath("$.image").value("updated_game_image_url"));

        
        Games updatedGame = gameRepository.findById(game.getId()).orElseThrow();
        assertEquals("Updated Game", updatedGame.getName());
        assertEquals(69.99, updatedGame.getPrice());
        assertEquals("updated_game_image_url", updatedGame.getImage());
    }

    @Test
    public void testDeleteGame() throws Exception {

        
        MvcResult result = mockMvc.perform(delete("/games/deleteGame/" + game.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) 
                .andExpect(jsonPath("$.id", is(game.getId().intValue()))) 
                .andReturn(); 

        
        String jsonResponse = result.getResponse().getContentAsString();
        System.out.println("Resposta JSON: " + jsonResponse);
    }
}


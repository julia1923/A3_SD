package com.example.fabel.Services;

import com.example.fabel.model.Games;
import com.example.fabel.model.Store;
import com.example.fabel.model.Users;
import com.example.fabel.repository.GameRepository;
import com.example.fabel.repository.StoreRepository;
import com.example.fabel.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
public class StoreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private StoreRepository storeRepository;

    private Users user;
    private Games game;
    private Store store;

    @BeforeEach
    public void setup() {
        storeRepository.deleteAll();
        gameRepository.deleteAll();
        userRepository.deleteAll();

        user = new Users();
        user.setUsername("testUser");
        user.setEmail("testUser@example.com");
        user.setPassword("securepassword");
        user.setAvatar("avatar_url");
        user = userRepository.save(user);

        game = new Games();
        game.setName("Test Game");
        game.setPrice(59.99);
        game.setImage("game_image_url");
        game = gameRepository.save(game);

        store = new Store();
        store.setUser(user);
        store.setGame(game);
        store = storeRepository.save(store);
    }


    @Test
    public void testUpdateStore() throws Exception {
        Store store = new Store();
        store.setUser(user);
        store.setGame(game);

        String storeJson = objectMapper.writeValueAsString(store);

        mockMvc.perform(put("/store/updateStore")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(storeJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user.id", is(user.getId().intValue())))
                .andExpect(jsonPath("$.game.id", is(game.getId().intValue()))); 
    }

    @Test
    public void testCreateStore() throws Exception {

        Store store = new Store();

        store.setUser(user);
        store.setGame(game);

        String storeJson = objectMapper.writeValueAsString(store);

        mockMvc.perform(post("/store/addNewStore")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(storeJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user.id", is(user.getId().intValue())))
                .andExpect(jsonPath("$.game.id", is(game.getId().intValue()))); 
    }

    @Test
    public void testDeleteStore() throws Exception {

        String storeJson = objectMapper.writeValueAsString(store);

        mockMvc.perform(delete("/store/deleteStore/" + store.getIdStore())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(storeJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user.id", is(user.getId().intValue())))
                .andExpect(jsonPath("$.game.id", is(game.getId().intValue()))); 
    }

    @Test
    public void testGetAllStore() throws Exception {
        mockMvc.perform(get("/store/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetStoreByUserId() throws Exception {
        mockMvc.perform(get("/store/user/" + user.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}

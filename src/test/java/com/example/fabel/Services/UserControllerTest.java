package com.example.fabel.Services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import com.example.fabel.model.Users;
import com.example.fabel.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        userRepository.deleteAll();
    }

    @Test
    public void testGetAllUsers() throws Exception {
    
        Users user = new Users();
        user.setUsername("testUser");
        user.setEmail("testUser@example.com");
        user.setPassword("securepassword");
        user.setAvatar("avatar_url");
        userRepository.save(user);

        
        mockMvc.perform(get("/users/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateUser() throws Exception {
        
        Users newUser = new Users();
        newUser.setUsername("newUser");
        newUser.setEmail("newUser@example.com");
        newUser.setPassword("securepassword");
        newUser.setAvatar("new_avatar_url");

        String userJson = objectMapper.writeValueAsString(newUser);

        mockMvc.perform(post("/users/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("newUser"))
                .andExpect(jsonPath("$.email").value("newUser@example.com"))
                .andExpect(jsonPath("$.avatar").value("new_avatar_url"));

       
        assertEquals(1, userRepository.count());
    }

    @Test
    public void testUpdateUser() throws Exception {
        
        Users user = new Users();
        user.setUsername("testUser");
        user.setEmail("testUser@example.com");
        user.setPassword("securepassword");
        user.setAvatar("avatar_url");
        user = userRepository.save(user);

        
        user.setUsername("updatedUser");
        user.setEmail("updatedUser@example.com");
        user.setPassword("newpassword");
        user.setAvatar("updated_avatar_url");

        String userJson = objectMapper.writeValueAsString(user);

        mockMvc.perform(put("/users/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("updatedUser"))
                .andExpect(jsonPath("$.email").value("updatedUser@example.com"))
                .andExpect(jsonPath("$.avatar").value("updated_avatar_url"));

        
        Users updatedUser = userRepository.findById(user.getId()).orElseThrow();
        assertEquals("updatedUser", updatedUser.getUsername());
        assertEquals("updatedUser@example.com", updatedUser.getEmail());
    }

    @Test
    public void testDeleteUser() throws Exception {
        
        Users user = new Users();
        user.setUsername("testUser");
        user.setEmail("testUser@example.com");
        user.setPassword("securepassword");
        user.setAvatar("avatar_url");
        user = userRepository.save(user);

        
        mockMvc.perform(delete("/users/delete/" + user.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("testUser"))
                .andExpect(jsonPath("$.email").value("testUser@example.com"))
                .andExpect(jsonPath("$.avatar").value("avatar_url"));

        
        assertTrue(userRepository.findById(user.getId()).isEmpty());
    }

    @Test
    public void testFindById() throws Exception {
        
        Users user = new Users();
        user.setUsername("testUser");
        user.setEmail("testUser@example.com");
        user.setPassword("securepassword");
        user.setAvatar("avatar_url");
        user = userRepository.save(user);

       
        mockMvc.perform(get("/users/find/" + user.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("testUser"))
                .andExpect(jsonPath("$.email").value("testUser@example.com"))
                .andExpect(jsonPath("$.avatar").value("avatar_url"));
    }
}

package com.example.fabel.Services;

import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import com.example.fabel.controller.UserController;
import com.example.fabel.model.Users;
import com.example.fabel.repository.UserRepository;

@WebMvcTest(UserController.class)
public class UserServiceTest {
  
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository usersRepository;

    @Test
    void testGetAllNotes() throws Exception {
        // Mock data
        Users user1 = new Users();
        user1.setId(1L);
        user1.setUsername("juan");
        user1.setEmail("juan@gmail.com");
        user1.setPassword("123");

        Users user2 = new Users();
        user2.setId(2L);
        user2.setUsername("luzia");
        user2.setEmail("luzia@gmail.com");
        user2.setPassword("456");

        // Configurar o comportamento do mock
        when(usersRepository.findAll()).thenReturn(List.of(user1, user2));

        // Simular a requisição ao endpoint e verificar os resultados
        mockMvc.perform(get("/users/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value("juan"))
                .andExpect(jsonPath("$[0].email").value("juan@gmail.com"))
                .andExpect(jsonPath("$[1].username").value("luzia"))
                .andExpect(jsonPath("$[1].email").value("luzia@gmail.com"));
    }
}

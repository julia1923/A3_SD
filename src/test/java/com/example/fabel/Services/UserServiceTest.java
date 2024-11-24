package com.example.fabel.Services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import com.example.fabel.controller.UserController; // Added import
import com.example.fabel.model.Users;
import com.example.fabel.repository.UserRepository;

class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private MultipartFile avatarFile;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser(){

        Users user = new Users();

        user.setUsername("newUser");
        user.setEmail("newemail@gmail.com");
        user.setPassword("newpassword");
        user.setAvatar("");

        when(userRepository.save(any(Users.class))).thenReturn(user);

        Users createdUser = userRepository.save(user);


        assertNotNull(createdUser);
        assertEquals("newUser", createdUser.getUsername());
        verify(userRepository).save(any(Users.class));
    }

    @Test
    void testGetAllUsers() {
        when(userRepository.findAll()).thenReturn(List.of(new Users(), new Users()));

        var users = userController.getAllUsers();

        assertNotNull(users);
        assertEquals(2, users.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        Users user = new Users();
        user.setId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        var result = userController.findById(1L);

        assertEquals(1L, result.getId());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void testDelete(){
        Users user = new Users();

        user.setId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        var result = userController.deleteUser(1L);

        assertEquals(1L, result.getId());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateUser() {

        Users existingUser = new Users();
        existingUser.setId(1L);
    

        Users newUser = new Users();
        newUser.setId(1L);
        newUser.setUsername("newUsername");
        newUser.setEmail("newEmail@example.com");
        newUser.setPassword("newPassword");
        newUser.setAvatar("newAvatar");
    

        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(Users.class))).thenReturn(newUser);
    
        // Execução do método
        Users result = userController.updateUsers(newUser);
    
        // Verificase o novo usuário não é nulo e foi salvo
        assertNotNull(result);
        verify(userRepository).save(any(Users.class));
    }

    @Test
    void testUpdateAvatarSuccess() throws Exception {
    // Mock de um usuário existente
    Users existingUser = new Users();
    existingUser.setId(1L);
    existingUser.setAvatar("oldAvatarBase64");

    // Mock de um arquivo de avatar
    MockMultipartFile avatarFile = new MockMultipartFile(
        "avatar", 
        "avatar.png", 
        "image/png", 
        "fakeImageContent".getBytes()
    );

    // Mock do comportamento do repositório
    when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
    when(userRepository.save(any(Users.class))).thenReturn(existingUser);

    // Execução do método
    ResponseEntity<?> response = userController.updateAvatar("1", avatarFile);

    // Verificação de sucesso: status HTTP 201
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    
    // Verificação se o avatar foi atualizado
    Users updatedUser = (Users) response.getBody();
    assertNotNull(updatedUser);
    assertNotEquals("oldAvatarBase64", updatedUser.getAvatar()); // O avatar foi alterado
    }
}
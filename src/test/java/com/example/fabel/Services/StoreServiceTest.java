package com.example.fabel.Services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.fabel.controller.StoreController;
import com.example.fabel.model.Games;
import com.example.fabel.model.Store;
import com.example.fabel.model.Users;
import com.example.fabel.repository.GameRepository;
import com.example.fabel.repository.StoreRepository;
import com.example.fabel.repository.UserRepository;

public class StoreServiceTest {
    
    @InjectMocks
    private StoreController storeController;

    @Mock
    private StoreRepository storeRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private GameRepository gameRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // read
    @Test
    void testGetAllStore (){
        when(storeRepository.findAll()).thenReturn(List.of(new Store()));

        var store = storeController.getAllStore();

        assertNotNull(store);
        assertEquals(1, store.size());
        verify(storeRepository, times(1)).findAll();
    }

    //create
    @Test
    void testCreateStore() {

         Users user = new Users();

        user.setUsername("newUser");
        user.setEmail("newemail@gmail.com");
        user.setPassword("newpassword");
        user.setAvatar("");

        when(userRepository.save(any(Users.class))).thenReturn(user);

        Users createdUser = userRepository.save(user);
        
        Store store = new Store();
        
        store.setUser(createdUser);

        Games game = new Games();
        game.setName("newGame");
        game.setPrice(1.1);
        game.setImage("");

        when(gameRepository.save(any(Games.class))).thenReturn(game);

        Games createdGame = gameRepository.save(game);

        store.setGame(createdGame);

        when(storeRepository.save(any(Store.class))).thenReturn(store);

        Store createdStore = storeRepository.save(store); // Chamar o método do controlador

        assertNotNull(createdStore);
        assertEquals("newStore", createdStore.getUser());
        verify(storeRepository).save(any(Store.class));
    }

    //update
    void testUpdateStore() {

        Users user = new Users();

        user.setUsername("newUser");
        user.setEmail("newemail@gmail.com");
        user.setPassword("newpassword");
        user.setAvatar("");

        when(userRepository.save(any(Users.class))).thenReturn(user);

        Users createdUser = userRepository.save(user);
        
        Store store = new Store();
        
        store.setUser(createdUser);

        Games game = new Games();
        game.setName("newGame");
        game.setPrice(1.1);
        game.setImage("");

        when(gameRepository.save(any(Games.class))).thenReturn(game);

        Games createdGame = gameRepository.save(game);

        store.setGame(createdGame);
    
        when(storeRepository.findById(1L)).thenReturn(Optional.of(store));
        when(storeRepository.save(any(Store.class))).thenReturn(store);
    
        // Execução do método
        Store result = gameController.updateGame(newGame);
    
        // Verifica se o novo usuário não é nulo e foi salvo
        assertNotNull(result);
        verify(gameRepository).save(any(Games.class));

    }
    //delete
    @Test
    void testDeleteStore() {
  
        Store store = new Store();

        store.setIdStore(1L);

        when(storeRepository.findById(1L)).thenReturn(Optional.of(store));

        var result = storeController.deleteStore(1L);

        assertEquals(1L, result.getIdStore());
        verify(gameRepository, times(1)).findById(1L);
    }
}

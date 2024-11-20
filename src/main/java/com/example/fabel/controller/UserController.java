package com.example.fabel.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.fabel.repository.UserRepository;
import com.example.fabel.model.Users;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping(value="/all")
    public List<Users> getAllUsers(){
        return userRepository.findAll();
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Users creatNewUser(@RequestBody Users user){
        
        Users createUser = new Users();
        createUser.setUsername(user.getUsername());
        createUser.setEmail(user.getEmail());
        createUser.setPassword(passwordEncoder.encode(user.getPassword()));
        createUser.setAvatar(user.getAvatar());

        return userRepository.save(createUser);
    }


    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Users updateUsers(@RequestBody Users user){
        
        Users getUser = userRepository.findById(user.getId()).orElseThrow();

        Users updateUser = new Users();
        updateUser.setId(getUser.getId());
        updateUser.setUsername(user.getUsername());
        updateUser.setEmail(user.getEmail());
        updateUser.setPassword(passwordEncoder.encode(user.getPassword()));
        updateUser.setAvatar(user.getAvatar());

        return userRepository.save(updateUser);
    }

    @PutMapping(value = "/updateAvatar", consumes = { "multipart/form-data" })
    public ResponseEntity<?> updateAvatar(@RequestParam("id") String idString, @RequestParam("avatar") MultipartFile avatar) throws IOException {

        Long id = Long.parseLong(idString);
        Users getUser = userRepository.findById(id).orElseThrow();

        String avatarBase64 = Base64.getEncoder().encodeToString(avatar.getBytes());

        Users updateUser = new Users();
        updateUser.setId(getUser.getId());
        updateUser.setAvatar(avatarBase64);

        return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(updateUser));
    }


    @DeleteMapping(value="/delete/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Users deleteUser(@PathVariable Long id){
        Users getUser = userRepository.findById(id).orElseThrow();
        userRepository.delete(getUser);
        return getUser;
    }

    @GetMapping(value = "/sucess")
    public String sucess() {

        Long id = (long) 1;

        Users getUser = userRepository.findById(id).orElseThrow();

        String sla = "<img src="+getUser.getAvatar()+"/>";
        return sla;
    }
}
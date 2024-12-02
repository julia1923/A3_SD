package com.example.fabel.controller;

import com.example.fabel.Security.JwtTokenUtil;
import com.example.fabel.model.Users;
import com.example.fabel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<Object> login(@RequestParam String email, @RequestParam String password) {
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Email e senha são obrigatórios.");
        }

        Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Usuário ou senha inválidos."));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Usuário ou senha inválidos.");
        }

        // Gerar o token JWT com username e email
        String token = jwtTokenUtil.generateToken(user.getUsername(), user.getEmail());

        // Criar o payload de resposta com a chave "Bearer" e o token
        return ResponseEntity.ok().body("{\"Bearer\": \"" + token + "\"}");
    }
}

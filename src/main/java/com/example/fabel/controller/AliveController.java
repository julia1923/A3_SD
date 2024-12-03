package com.example.fabel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AliveController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/")
    public String rootRouter() {
        return "Alive";
    }

    @GetMapping("/test_connection")
    public String testConnection() {
        try {
            jdbcTemplate.execute("SELECT 1");
            return "Connection Success";
        } catch (Exception e) {
            return "Connection Failed: " + e.getMessage();
        }
    }
}
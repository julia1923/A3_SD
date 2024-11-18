package com.example.fabel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fabel.model.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
    
}
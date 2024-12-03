package com.example.fabel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fabel.model.Users;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String email);
}
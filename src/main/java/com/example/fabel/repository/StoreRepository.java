package com.example.fabel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fabel.model.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {

    List<Store> findByUserId(Long userId);

}

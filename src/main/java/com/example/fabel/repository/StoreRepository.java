package com.example.fabel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fabel.model.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {

}

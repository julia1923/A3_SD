package com.example.fabel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fabel.model.Store;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StoreRepository extends JpaRepository<Store, Long> {
    @Query("SELECT s FROM Store s WHERE s.user.id = :userId")
    List<Store> findByUserId(@Param("userId") Long userId);
}

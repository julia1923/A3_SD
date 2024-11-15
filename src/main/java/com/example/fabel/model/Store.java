package com.example.fabel.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "Store")

public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idStore;

    @ManyToOne
    @JoinColumn(name = "id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "id")
    private Games game;
}
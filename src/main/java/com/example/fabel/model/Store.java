package com.example.fabel.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "Store")
@EqualsAndHashCode
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idStore;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "idGame")
    private Games game;
}
package com.example.fabel.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "Games")
public class Games {
    

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Double price;

    @Column(name = "image", columnDefinition = "LONGTEXT")
    private String image;
}
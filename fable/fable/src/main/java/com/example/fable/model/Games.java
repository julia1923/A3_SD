package com.example.fable.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "Games")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Games")
@EqualsAndHashCode(of = "id") // indica representação única na tabela
public class Games {

    // id é a chave primária da tabela e será gerada automaticamente.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private double price;
}
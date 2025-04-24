package com.SySTomateAlgo.TomateAlgo.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Barra")
public class Barra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;


    public Barra() {
    }

    public Barra(String name) {
        this.name = name;
    }

    public Barra(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

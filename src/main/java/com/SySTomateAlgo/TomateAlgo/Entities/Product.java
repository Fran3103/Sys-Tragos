package com.SySTomateAlgo.TomateAlgo.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Productos")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String type;

    private String subType;

    private String alcoholType;

    private Double capacity;

    private Integer stock;


    public Product(Long id, String name, String type, String subType, String alcoholType, Double capacity, Integer stock) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.subType = subType;
        this.alcoholType = alcoholType;
        this.capacity = capacity;
        this.stock = stock;
    }

    public Product(String name, String type, String subType, String alcoholType, Double capacity, Integer stock) {
        this.name = name;
        this.type = type;
        this.subType = subType;
        this.alcoholType = alcoholType;
        this.capacity = capacity;
        this.stock = stock;
    }

    public Product() {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getAlcoholType() {
        return alcoholType;
    }

    public void setAlcoholType(String alcoholType) {
        this.alcoholType = alcoholType;
    }

    public Double getCapacity() {
        return capacity;
    }

    public void setCapacity(Double capacity) {
        this.capacity = capacity;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}

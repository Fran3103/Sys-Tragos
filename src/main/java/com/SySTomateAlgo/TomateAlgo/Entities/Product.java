package com.SySTomateAlgo.TomateAlgo.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "Productos")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JsonProperty("productType")
    @Enumerated(EnumType.STRING)
    @Column(name = "product_type")
    private ProductType productType;

    @JsonProperty("alcoholType")
    @Enumerated(EnumType.STRING)
    @Column(name = "alcohol_type")
    private AlcoholType alcoholType;

    private Double capacity;

    private Integer stock;


    public Product(Long id, String name, ProductType productType, AlcoholType alcoholType, Double capacity, Integer stock) {
        this.id = id;
        this.name = name;
        this.productType = productType;
        this.alcoholType = alcoholType;
        this.capacity = capacity;
        this.stock = stock;
    }

    public Product(String name, ProductType productType, AlcoholType alcoholType, Double capacity, Integer stock) {
        this.name = name;
        this.productType = productType;
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

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public AlcoholType getAlcoholType() {
        return alcoholType;
    }

    public void setAlcoholType(AlcoholType alcoholType) {
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

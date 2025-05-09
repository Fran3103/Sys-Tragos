package com.SySTomateAlgo.TomateAlgo.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

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

    private Double incidence;

    private Integer capacityPerBox;

    @ManyToMany(mappedBy = "cristaleria")
    @JsonIgnore
    private List<Service> service = new ArrayList<>();


    public Product(Long id, String name, ProductType productType, AlcoholType alcoholType, Double capacity, Integer stock, Double incidence, Integer capacityPerBox, List<Service> service) {
        this.id = id;
        this.name = name;
        this.productType = productType;
        this.alcoholType = alcoholType;
        this.capacity = capacity;
        this.stock = stock;
        this.incidence = incidence;
        this.capacityPerBox = capacityPerBox;
        this.service = service;
    }

    public Product(String name, ProductType productType, AlcoholType alcoholType, Double capacity, Integer stock, Double incidence, Integer capacityPerBox, List<Service> service) {
        this.name = name;
        this.productType = productType;
        this.alcoholType = alcoholType;
        this.capacity = capacity;
        this.stock = stock;
        this.incidence = incidence;
        this.capacityPerBox = capacityPerBox;
        this.service = service;
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

    public Double getIncidence() {
        return incidence;
    }

    public void setIncidence(Double incidence) {
        this.incidence = incidence;
    }


    public Integer getCapacityPerBox() {
        return capacityPerBox;
    }

    public void setCapacityPerBox(Integer capacityPerBox) {
        this.capacityPerBox = capacityPerBox;
    }

    public List<Service> getService() {
        return service;
    }

    public void setService(List<Service> service) {
        this.service = service;
    }
}

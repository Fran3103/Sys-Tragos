package com.SySTomateAlgo.TomateAlgo.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "cocktail_ingredient")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class CocktailIngredients {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cocktail_id", nullable = false)
    @JsonBackReference
    private Cocktail cocktail;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "ounces", nullable = false)
    private Double ounces;

    public CocktailIngredients() {
    }

    public CocktailIngredients(Double ounces, Product product, Cocktail cocktail) {
        this.ounces = ounces;
        this.product = product;
        this.cocktail = cocktail;
    }

    public CocktailIngredients(Long id, Cocktail cocktail, Product product, Double ounces) {
        this.id = id;
        this.cocktail = cocktail;
        this.product = product;
        this.ounces = ounces;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cocktail getCocktail() {
        return cocktail;
    }

    public void setCocktail(Cocktail cocktail) {
        this.cocktail = cocktail;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getOunces() {
        return ounces;
    }

    public void setOunces(Double ounces) {
        this.ounces = ounces;
    }
}

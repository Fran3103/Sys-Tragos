package com.SySTomateAlgo.TomateAlgo.Entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cocktails")
public class Cocktail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "cocktail_ingredients",
            joinColumns = @JoinColumn(name = "cocktail_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> ingredients;

    public Cocktail() {
    }

    public Cocktail(String name, String description, List<Product> ingredients) {
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
    }

    public Cocktail(Long id, String name, String description, List<Product> ingredients) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Product> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Product> ingredients) {
        this.ingredients = ingredients;
    }
}

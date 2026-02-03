package com.SySTomateAlgo.TomateAlgo.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cocktails")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Cocktail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @OneToMany(
            mappedBy = "cocktail",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonManagedReference
    private List<CocktailIngredients> ingredients = new ArrayList<>();

    public Cocktail() {
    }

    public Cocktail(String name, String description, List<CocktailIngredients> ingredients) {
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
    }

    public Cocktail(Long id, String name, String description, List<CocktailIngredients> ingredients) {
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

    public List<CocktailIngredients> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<CocktailIngredients> ingredients) {
        this.ingredients = ingredients;
    }

    public void addIngredient(CocktailIngredients ci) {
        ingredients.add(ci);
        ci.setCocktail(this);
    }
    public void removeIngredient(CocktailIngredients ci) {
        ingredients.remove(ci);
        ci.setCocktail(null);
    }
}

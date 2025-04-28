package com.SySTomateAlgo.TomateAlgo.DTOs;

import java.util.List;

public class CocktailRequestDTO {

    private String name;

    private String description;

    private List<CocktailIngredientDTO> ingredients;


    public CocktailRequestDTO() {
    }

    public CocktailRequestDTO(String name, List<CocktailIngredientDTO> ingredients, String description) {
        this.name = name;
        this.ingredients = ingredients;
        this.description = description;
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

    public List<CocktailIngredientDTO> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<CocktailIngredientDTO> ingredients) {
        this.ingredients = ingredients;
    }
}

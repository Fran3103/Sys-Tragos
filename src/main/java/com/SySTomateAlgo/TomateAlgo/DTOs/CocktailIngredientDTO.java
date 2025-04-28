package com.SySTomateAlgo.TomateAlgo.DTOs;

public class CocktailIngredientDTO {

    private Long productId;
    private Double ounces;


    public CocktailIngredientDTO() {
    }

    public CocktailIngredientDTO(Long productId, Double ounces) {
        this.productId = productId;
        this.ounces = ounces;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Double getOunces() {
        return ounces;
    }

    public void setOunces(Double ounces) {
        this.ounces = ounces;
    }
}

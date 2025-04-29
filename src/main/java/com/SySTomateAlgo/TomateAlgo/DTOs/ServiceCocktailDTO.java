package com.SySTomateAlgo.TomateAlgo.DTOs;

public class ServiceCocktailDTO {
    private Long cocktailId;
    private Integer incidence;

    public ServiceCocktailDTO() {}

    public ServiceCocktailDTO(Long cocktailId, Integer incidence) {
        this.cocktailId = cocktailId;
        this.incidence = incidence;
    }

    public Long getCocktailId() {
        return cocktailId;
    }

    public void setCocktailId(Long cocktailId) {
        this.cocktailId = cocktailId;
    }

    public Integer getIncidence() {
        return incidence;
    }

    public void setIncidence(Integer incidence) {
        this.incidence = incidence;
    }
}
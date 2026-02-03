package com.SySTomateAlgo.TomateAlgo.dtos;

public class ServiceCocktailDTO {
    private Long cocktailId;
    private Integer incidence;
    private String cocktailName;

    public ServiceCocktailDTO() {}

    public ServiceCocktailDTO(Long cocktailId, Integer incidence, String cocktailName) {
        this.cocktailId = cocktailId;
        this.incidence = incidence;
        this.cocktailName = cocktailName;
    }

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

    public String getCocktailName() {
        return cocktailName;
    }

    public void setCocktailName(String cocktailName) {
        this.cocktailName = cocktailName;
    }
}
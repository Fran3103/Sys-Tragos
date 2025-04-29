package com.SySTomateAlgo.TomateAlgo.DTOs;

import java.util.ArrayList;
import java.util.List;

public class ServiceDTO {
    private Long id;
    private String name;
    private List<ServiceCocktailDTO> cocktails = new ArrayList<>();

    public ServiceDTO() {}

    public ServiceDTO(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public ServiceDTO(Long id, String name, List<ServiceCocktailDTO> cocktails) {
        this.id = id;
        this.name = name;
        this.cocktails = cocktails != null ? cocktails : new ArrayList<>();
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

    public List<ServiceCocktailDTO> getCocktails() {
        return cocktails;
    }

    public void setCocktails(List<ServiceCocktailDTO> cocktails) {
        this.cocktails = cocktails;
    }
}

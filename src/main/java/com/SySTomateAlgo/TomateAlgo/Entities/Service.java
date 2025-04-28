package com.SySTomateAlgo.TomateAlgo.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Service")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;

    @ManyToMany
    @JoinTable(
            name = "service_cocktail",
            joinColumns  = @JoinColumn(name = "service_id"),
            inverseJoinColumns = @JoinColumn(name = "cocktail_id")
    )
    private List<Cocktail> cocktails;

    public Service() {
    }

    public Service(String name, List<Cocktail> cocktails) {
        this.name = name;
        this.cocktails = cocktails;
    }

    public Service(Long id, String name,List<Cocktail> cocktails) {
        this.id = id;
        this.name = name;
        this.cocktails = cocktails;
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

    public List<Cocktail> getCocktails() {
        return cocktails;
    }

    public void setCocktails(List<Cocktail> cocktails) {
        this.cocktails = cocktails;
    }
}

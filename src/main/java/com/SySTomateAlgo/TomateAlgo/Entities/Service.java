package com.SySTomateAlgo.TomateAlgo.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "services")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;



    @OneToMany(mappedBy = "service",fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ServiceCocktail> cocktails = new ArrayList<>();

    public Service() {
    }

    public Service(String name) {
        this.name = name;
    }


    public Service(Long id, String name, List<ServiceCocktail> cocktails) {
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

    public List<ServiceCocktail> getCocktails() {
        return cocktails;
    }

    public void setCocktails(List<ServiceCocktail> cocktails) {
        this.cocktails = cocktails;
    }

    public void addServiceCocktail(ServiceCocktail sc) {
        cocktails.add(sc);
        sc.setService(this);
    }

    public void removeServiceCocktail(ServiceCocktail sc) {
        cocktails.remove(sc);
        sc.setService(null);
    }
}

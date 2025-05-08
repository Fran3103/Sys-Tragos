package com.SySTomateAlgo.TomateAlgo.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Barra {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    @JsonBackReference
    private Event event;

    @OneToMany(mappedBy = "barra", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<BarraItem> equipments = new ArrayList<>();

    private String name;


    public Barra() {
    }

    public Barra(Event event, List<BarraItem> equipments, String name) {
        this.event = event;
        this.equipments = equipments;
        this.name = name;
    }

    public Barra(Long id, Event event, List<BarraItem> equipments, String name) {
        this.id = id;
        this.event = event;
        this.equipments = equipments;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public List<BarraItem> getEquipments() {
        return equipments;
    }

    public void setEquipments(List<BarraItem> equipments) {
        this.equipments = equipments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

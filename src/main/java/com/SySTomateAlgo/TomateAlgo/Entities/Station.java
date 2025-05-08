package com.SySTomateAlgo.TomateAlgo.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Station {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    @JsonBackReference
    private Event event;

    @OneToMany(mappedBy = "station", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<StationEquipment> equipments = new ArrayList<>();

    private String name;


    public Station() {
    }

    public Station(Event event, List<StationEquipment> equipments, String name) {
        this.event = event;
        this.equipments = equipments;
        this.name = name;
    }

    public Station(Long id, Event event, List<StationEquipment> equipments, String name) {
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

    public List<StationEquipment> getEquipments() {
        return equipments;
    }

    public void setEquipments(List<StationEquipment> equipments) {
        this.equipments = equipments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

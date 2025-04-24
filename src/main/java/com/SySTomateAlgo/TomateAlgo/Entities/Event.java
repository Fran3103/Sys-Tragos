package com.SySTomateAlgo.TomateAlgo.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private String location;

    private Integer invitaCant;

    private String details;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;

    @ManyToOne
    @JoinColumn(name = "barra_id")
    private Barra barra;

    public Event() {
    }

    public Event(LocalDate date, String location, Integer invitaCant, String details, Client client, Service service, Barra barra) {
        this.date = date;
        this.location = location;
        this.invitaCant = invitaCant;
        this.details = details;
        this.client = client;
        this.service = service;
        this.barra = barra;
    }

    public Event(Long id, LocalDate date, String location, Integer invitaCant, String details, Client client, Service service, Barra barra) {
        this.id = id;
        this.date = date;
        this.location = location;
        this.invitaCant = invitaCant;
        this.details = details;
        this.client = client;
        this.service = service;
        this.barra = barra;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getInvitaCant() {
        return invitaCant;
    }

    public void setInvitaCant(Integer invitaCant) {
        this.invitaCant = invitaCant;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Barra getBarra() {
        return barra;
    }

    public void setBarra(Barra barra) {
        this.barra = barra;
    }
}

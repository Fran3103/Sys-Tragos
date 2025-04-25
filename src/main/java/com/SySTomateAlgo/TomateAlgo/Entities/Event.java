package com.SySTomateAlgo.TomateAlgo.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

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

    private String status;

    private String typeEvent;

    private LocalTime startTime;

    private LocalTime endTime;

    private LocalTime setupTime;

    private String setupNote;



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

    public Event(Barra barra, Service service, Client client, String setupNote, LocalTime setupTime, LocalTime endTime, LocalTime startTime, String typeEvent, String status, String details, Integer invitaCant, String location, LocalDate date) {
        this.barra = barra;
        this.service = service;
        this.client = client;
        this.setupNote = setupNote;
        this.setupTime = setupTime;
        this.endTime = endTime;
        this.startTime = startTime;
        this.typeEvent = typeEvent;
        this.status = status;
        this.details = details;
        this.invitaCant = invitaCant;
        this.location = location;
        this.date = date;
    }

    public Event(Long id, LocalDate date, String location, Integer invitaCant, String details, String status, String typeEvent, LocalTime startTime, LocalTime endTime, LocalTime setupTime, String setupNote, Client client, Service service, Barra barra) {
        this.id = id;
        this.date = date;
        this.location = location;
        this.invitaCant = invitaCant;
        this.details = details;
        this.status = status;
        this.typeEvent = typeEvent;
        this.startTime = startTime;
        this.endTime = endTime;
        this.setupTime = setupTime;
        this.setupNote = setupNote;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTypeEvent() {
        return typeEvent;
    }

    public void setTypeEvent(String typeEvent) {
        this.typeEvent = typeEvent;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public LocalTime getSetupTime() {
        return setupTime;
    }

    public void setSetupTime(LocalTime setupTime) {
        this.setupTime = setupTime;
    }

    public String getSetupNote() {
        return setupNote;
    }

    public void setSetupNote(String setupNote) {
        this.setupNote = setupNote;
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

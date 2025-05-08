package com.SySTomateAlgo.TomateAlgo.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Events")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@SuppressWarnings("null")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private String location;

    @NotNull(message= "La cantidad de invitados  es obligatoria para poder crear el evento.")
    private Integer invitaCant;

    private String details;

    private String status;


    @JsonProperty("typeEvent")
    @Enumerated(EnumType.STRING)
    @Column(name = "typeEvent")
    private EventType typeEvent;


    @JsonProperty("ageInvita")
    @Enumerated(EnumType.STRING)
    @Column(name = "ageInvita")
    private AgeInvita ageInvita;


    @JsonProperty("climateType")
    @Enumerated(EnumType.STRING)
    @Column(name = "climate_Type")
    private ClimateType climateType;


    private LocalTime startTime;

    private LocalTime endTime;

    private LocalTime setupTime;

    private String setupNote;

    @Column(name = "duration_hours")
    private Double duration;


    @ManyToOne
    @JoinColumn(name = "client_id")
    @NotNull(message= "El cliente es obligatorio para poder crear el evento.")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "service_id")
    @NotNull(message= "El servicio es obligatorio para poder crear el evento.")
    private Service service;


    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Barra> barra = new ArrayList<>();

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Station> stations = new ArrayList<>();

    public Event() {
    }

    public Event(List<Barra> barra, Service service, Client client, String setupNote, LocalTime setupTime, LocalTime endTime, LocalTime startTime, EventType typeEvent, String status, String details, Integer invitaCant, String location, LocalDate date, ClimateType climateType, AgeInvita ageInvita, double duration, List<Station> stations) {
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
        this.climateType = climateType;
        this.ageInvita= ageInvita;
        this.duration = duration;
        this.stations = stations;
    }

    public Event(Long id, LocalDate date, String location, Integer invitaCant, String details, String status, EventType typeEvent, LocalTime startTime, LocalTime endTime, LocalTime setupTime, String setupNote, Client client, Service service, List<Barra> barra, ClimateType climateType, AgeInvita ageInvita, double duration, List<Station> stations) {
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
        this.climateType = climateType;
        this.ageInvita = ageInvita;
        this.duration = duration;
        this.stations = stations;
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

    public @NotNull(message = "La cantidad de invitados  es obligatoria para poder crear el evento.") Integer getInvitaCant() {
        return invitaCant;
    }

    public void setInvitaCant(@NotNull(message = "La cantidad de invitados  es obligatoria para poder crear el evento.") Integer invitaCant) {
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

    public EventType getTypeEvent() {
        return typeEvent;
    }

    public void setTypeEvent(EventType typeEvent) {
        this.typeEvent = typeEvent;
    }

    public AgeInvita getAgeInvita() {
        return ageInvita;
    }

    public void setAgeInvita(AgeInvita ageInvita) {
        this.ageInvita = ageInvita;
    }

    public ClimateType getClimateType() {
        return climateType;
    }

    public void setClimateType(ClimateType climateType) {
        this.climateType = climateType;
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

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public @NotNull(message = "El cliente es obligatorio para poder crear el evento.") Client getClient() {
        return client;
    }

    public void setClient(@NotNull(message = "El cliente es obligatorio para poder crear el evento.") Client client) {
        this.client = client;
    }

    public @NotNull(message = "El servicio es obligatorio para poder crear el evento.") Service getService() {
        return service;
    }

    public void setService(@NotNull(message = "El servicio es obligatorio para poder crear el evento.") Service service) {
        this.service = service;
    }

    public List<Barra> getBarra() {
        return barra;
    }

    public void setBarra(List<Barra> barra) {
        this.barra = barra;
    }

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }
}

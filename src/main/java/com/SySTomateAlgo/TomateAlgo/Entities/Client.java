package com.SySTomateAlgo.TomateAlgo.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "Clients")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String phone;

    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "client_type", nullable = false)
    private ClientType clientType;


    public Client() {
    }

    public Client(String name, String email, String phone, String lastName, ClientType clientType) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.lastName = lastName;
        this.clientType = clientType;
    }

    public Client(Long id, String name, String email, String phone, String lastName, ClientType clientType) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.lastName = lastName;
        this.clientType = clientType;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ClientType getClientType() {
        return clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }
}

package com.SySTomateAlgo.TomateAlgo.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class BarraItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "barra_id")
    @JsonBackReference
    private Barra barra;

    public BarraItem() {
    }

    public BarraItem(Product product, int quantity, Barra barra) {
        this.product = product;
        this.quantity = quantity;
        this.barra = barra;
    }


    public BarraItem(Long id, Product product, int quantity, Barra barra) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.barra = barra;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Barra getBarras() {
        return barra;
    }

    public void setBarras(Barra barra) {
        this.barra = barra;
    }
}

package com.SySTomateAlgo.TomateAlgo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "ounces", nullable = false)
    private Double ounces;

    @Column(name = "units", nullable = false)
    private Integer units;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    @JsonBackReference
    private Order order;

    public OrderItem() {}

    public OrderItem(Product product, Double ounces, Integer units, Order order) {
        this.product = product;
        this.ounces = ounces;
        this.units = units;
        this.order = order;
    }

    public OrderItem(Long id, Product product, Double ounces, Integer units, Order order) {
        this.id = id;
        this.product = product;
        this.ounces = ounces;
        this.units = units;
        this.order = order;
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

    public Double getOunces() {
        return ounces;
    }

    public void setOunces(Double ounces) {
        this.ounces = ounces;
    }

    public Integer getUnits() {
        return units;
    }

    public void setUnits(Integer units) {
        this.units = units;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}

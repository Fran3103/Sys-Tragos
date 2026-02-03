package com.SySTomateAlgo.TomateAlgo.dtos;

public class OrderItemDTO {
    private String productName;
    private Double ounces;
    private Integer units;

    public OrderItemDTO() {}

    public OrderItemDTO(String productName, Double ounces, Integer units) {
        this.productName = productName;
        this.ounces = ounces;
        this.units = units;
    }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public Double getOunces() { return ounces; }
    public void setOunces(Double ounces) { this.ounces = ounces; }

    public Integer getUnits() { return units; }
    public void setUnits(Integer units) { this.units = units; }
}

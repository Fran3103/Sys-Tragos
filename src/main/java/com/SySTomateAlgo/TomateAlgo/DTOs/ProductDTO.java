package com.SySTomateAlgo.TomateAlgo.DTOs;

public class ProductDTO {
    private Long id;
    private String name;
    private String productType;


    public ProductDTO() {
    }

    public ProductDTO(String name, String productType) {
        this.name = name;
        this.productType = productType;
    }

    public ProductDTO(Long id, String name, String productType) {
        this.id = id;
        this.name = name;
        this.productType = productType;
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

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
}

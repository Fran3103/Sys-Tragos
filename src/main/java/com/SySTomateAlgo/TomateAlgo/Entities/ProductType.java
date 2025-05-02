package com.SySTomateAlgo.TomateAlgo.Entities;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ProductType {

    Insumo_Alcoholico,
    Insumo_No_Alcoholico,
    Fruta,
    Hielo,
    Cristaleria,
    Equipamiento,
    Herramientas,
    Insumos;


    @JsonCreator
    public static ProductType from(String key) {
        if (key == null || key.isBlank()) return null;
        String trimmed = key.trim();
        for (ProductType at : ProductType.values()) {
            if (at.name().equalsIgnoreCase(trimmed)) {
                return at;
            }
        }
        throw new IllegalArgumentException("Unknown ProductType: " + key);
    }
}

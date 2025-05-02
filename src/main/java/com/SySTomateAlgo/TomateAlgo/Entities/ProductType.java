package com.SySTomateAlgo.TomateAlgo.Entities;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ProductType {

    Insumo_Alcoholico,
    Insumo_No_Alcoholico,
    Fruta,
    Hielo,
    Cristaleria,
    Equipamiento,
    Herramientas;


    @JsonCreator
    public static ProductType from(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        String normalized = value
                .trim()
                .toUpperCase()

                .replaceAll("[^A-Z0-9]+", "_");
        try {
            return ProductType.valueOf(normalized);
        } catch (IllegalArgumentException ex) {
            return null;
        }
    }
}

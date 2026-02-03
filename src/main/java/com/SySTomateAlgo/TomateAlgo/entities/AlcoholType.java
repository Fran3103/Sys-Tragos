package com.SySTomateAlgo.TomateAlgo.entities;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum AlcoholType {

    Vodka,
    Ron_Blanco,
    Ron_Dorado,
    Cachaza,
    Fernet,
    Bitter_Rojo,
    Cynar,
    Aperol,
    Espumante,
    Gin,
    Tequila,
    Whisky,
    Vermut_Dulce,
    Vermut_Seco,
    Vermut_Rojo,
    Vino_Tinto,
    Vino_Blanco;

    @JsonCreator
    public static AlcoholType from(String key) {
        if (key == null || key.isBlank()) return null;
        String trimmed = key.trim();
        for (AlcoholType at : AlcoholType.values()) {
            if (at.name().equalsIgnoreCase(trimmed)) {
                return at;
            }
        }
        throw new IllegalArgumentException("Unknown AlcoholType: " + key);
    }

}

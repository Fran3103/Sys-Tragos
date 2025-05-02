package com.SySTomateAlgo.TomateAlgo.Entities;

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
    public static AlcoholType from(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        return AlcoholType.valueOf(value.trim().toUpperCase());
    }

}

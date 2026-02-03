package com.SySTomateAlgo.TomateAlgo.entities;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ClimateType {
    Caluroso,
    Muy_Caluroso,
    Templado,
    Frio;

    @JsonCreator
    public static ClimateType from(String key) {
        if (key == null || key.isBlank()) return null;
        String trimmed = key.trim();
        for (ClimateType at : ClimateType.values()) {
            if (at.name().equalsIgnoreCase(trimmed)) {
                return at;
            }
        }
        throw new IllegalArgumentException("Unknown ClimateType: " + key);
    }
}

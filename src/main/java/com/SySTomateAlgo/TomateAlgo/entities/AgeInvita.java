package com.SySTomateAlgo.TomateAlgo.entities;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum AgeInvita {

    Jovenes_18_a_25,
    Adultos_25_mas,
    Mayor_a_35;

    @JsonCreator
    public static AgeInvita from(String key) {
        if (key == null || key.isBlank()) return null;
        String trimmed = key.trim();
        for (AgeInvita at : AgeInvita.values()) {
            if (at.name().equalsIgnoreCase(trimmed)) {
                return at;
            }
        }
        throw new IllegalArgumentException("Unknown EventType: " + key);
    }
}

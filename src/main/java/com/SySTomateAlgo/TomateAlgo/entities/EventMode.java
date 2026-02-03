package com.SySTomateAlgo.TomateAlgo.entities;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum EventMode {

    Free_Bar,
    Pack;

    @JsonCreator
    public static EventMode from(String key) {
        if (key == null || key.isBlank()) return null;
        String trimmed = key.trim();
        for (EventMode at : EventMode.values()) {
            if (at.name().equalsIgnoreCase(trimmed)) {
                return at;
            }
        }
        throw new IllegalArgumentException("Unknown EventMode: " + key);
    }
}

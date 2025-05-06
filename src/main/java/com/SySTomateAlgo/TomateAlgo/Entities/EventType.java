package com.SySTomateAlgo.TomateAlgo.Entities;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum EventType {
    Cumpleaños,
    Casamiento,
    Corporativo,
    Exposicion,
    Cumpleaños_15;

    @JsonCreator
    public static EventType from(String key) {
        if (key == null || key.isBlank()) return null;
        String trimmed = key.trim();
        for (EventType at : EventType.values()) {
            if (at.name().equalsIgnoreCase(trimmed)) {
                return at;
            }
        }
        throw new IllegalArgumentException("Unknown EventType: " + key);
    }

    }

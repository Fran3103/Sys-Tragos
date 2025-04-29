package com.SySTomateAlgo.TomateAlgo.DTOs;

import java.time.LocalDate;
import java.util.List;

public class OrderDTO {

    private Long id;
    private LocalDate generatedAt;
    private Long eventId;
    private List<OrderItemDTO> items;

    public OrderDTO() {
    }

    public OrderDTO(Long id, LocalDate generatedAt, Long eventId, List<OrderItemDTO> items) {
        this.id = id;
        this.generatedAt = generatedAt;
        this.eventId = eventId;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getGeneratedAt() {
        return generatedAt;
    }

    public void setGeneratedAt(LocalDate generatedAt) {
        this.generatedAt = generatedAt;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public List<OrderItemDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDTO> items) {
        this.items = items;
    }
}

package com.SySTomateAlgo.TomateAlgo.Mapper;

import com.SySTomateAlgo.TomateAlgo.Entities.Order;
import com.SySTomateAlgo.TomateAlgo.Entities.OrderItem;
import com.SySTomateAlgo.TomateAlgo.DTOs.OrderDTO;
import com.SySTomateAlgo.TomateAlgo.DTOs.OrderItemDTO;

import java.util.stream.Collectors;

public class OrderMapper {
    public static OrderDTO toDto(Order order) {
        var items = order.getItems().stream()
                .map(OrderMapper::toItemDto)
                .collect(Collectors.toList());

        Long eventId = order.getEvent() != null ? order.getEvent().getId() : null;

        if (order.getEvent() == null){
            throw new IllegalStateException("No se puede crear una orden sin un evento asociado. id: " + order.getId());
        }
        if (order.getEvent() != null){
            eventId = order.getEvent().getId();

        }

        return new OrderDTO(
                order.getId(),
                order.getGeneratedAt(),
                eventId,
                items
        );
    }

    private static OrderItemDTO toItemDto(OrderItem item) {
        return new OrderItemDTO(
                item.getProduct().getName(),
                item.getOunces(),
                item.getUnits()
        );
    }
}
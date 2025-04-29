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

        return new OrderDTO(
                order.getId(),
                order.getGeneratedAt(),
                order.getEvent().getId(),
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
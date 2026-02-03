package com.SySTomateAlgo.TomateAlgo.services;

import com.SySTomateAlgo.TomateAlgo.entities.Event;
import com.SySTomateAlgo.TomateAlgo.entities.Order;

import java.util.*;

public interface OrderService {
    Order generateOrderFromEvent(Event event);
    List<Order> findAll();
    Optional<Order> findById(Long id);
    String delete(Long id);

}

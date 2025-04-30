package com.SySTomateAlgo.TomateAlgo.Services;

import com.SySTomateAlgo.TomateAlgo.Entities.Event;
import com.SySTomateAlgo.TomateAlgo.Entities.Order;

import java.util.*;

public interface OrderService {
    Order generateOrderFromEvent(Event event);
    List<Order> findAll();
    Optional<Order> findById(Long id);
    String delete(Long id);

}

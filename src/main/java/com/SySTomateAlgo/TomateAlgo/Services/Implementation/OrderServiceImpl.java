package com.SySTomateAlgo.TomateAlgo.Services.Implementation;

import com.SySTomateAlgo.TomateAlgo.Entities.*;
import com.SySTomateAlgo.TomateAlgo.Repositories.OrderRepository;
import com.SySTomateAlgo.TomateAlgo.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public Order generateOrderFromEvent(Event event) {
        Map<Product, Double> productTotals  = new HashMap<>();

        int guests = event.getInvitaCant();
        int estimatedDrinkPerPerson = 4;

        List<Cocktail> cocktails = event.getService().getCocktails();

        for (Cocktail cocktail : cocktails){
            for (Product ingredient : cocktail.getIngredients()){
                double totalAmount = guests * estimatedDrinkPerPerson * 1.0;
                productTotals.merge(ingredient,totalAmount,Double::sum);
            }
        }

        List<OrderItem> items = new ArrayList<>();
        Order order = new Order();
        order.setGeneratedAt(LocalDate.now());
        order.setEvent(event);

        for (Map.Entry<Product, Double> entry: productTotals.entrySet()){
            OrderItem item = new OrderItem();
            item.setProduct(entry.getKey());
            item.setQuantity(entry.getValue());
            item.setOrder(order);
            items.add(item);
        }

        order.setItems(items);
        return orderRepository.save(order);

    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }
}

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
            for (CocktailIngredients ci : cocktail.getIngredients()){
                Product product = ci.getProduct();
                double ouncesPerCocktail = ci.getOunces();

                double totalOnces = guests * estimatedDrinkPerPerson * ouncesPerCocktail;
                productTotals.merge(product,totalOnces,Double::sum);
            }
        }

        Order order = new Order();
        order.setEvent(event);
        List<OrderItem> items = productTotals.entrySet().stream()
                .map(e -> {
                    OrderItem li = new OrderItem();
                    li.setProduct(e.getKey());
                    li.setQuantity(e.getValue());
                    li.setOrder(order);
                    return li;
                })
                .toList();
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

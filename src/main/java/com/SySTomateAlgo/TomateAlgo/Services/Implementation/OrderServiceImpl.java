package com.SySTomateAlgo.TomateAlgo.Services.Implementation;

import com.SySTomateAlgo.TomateAlgo.Entities.*;
import com.SySTomateAlgo.TomateAlgo.Repositories.OrderRepository;
import com.SySTomateAlgo.TomateAlgo.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public Order generateOrderFromEvent(Event event) {
        Map<Product, Double> productTotals  = new HashMap<>();

        int guests = event.getInvitaCant();
        int estimatedDrinkPerPerson = 4;
        int totalDrink = guests * estimatedDrinkPerPerson;

       List<ServiceCocktail> scList = event.getService().getCocktails();
       int sumIncidences = scList.stream()
               .mapToInt(ServiceCocktail::getIncidence)
               .sum();

       for (ServiceCocktail sc : scList){
           int incidence = sc.getIncidence();
           double portion = totalDrink * (incidence/ (double) sumIncidences);
           int count = (int) Math.round(portion);

           for (CocktailIngredients ci : sc.getCocktail().getIngredients()){
               Product p = ci.getProduct();

               double ouncesNeeded = count *ci.getOunces();
               productTotals.merge( p , ouncesNeeded, Double::sum);
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
                .collect(Collectors.toList());
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

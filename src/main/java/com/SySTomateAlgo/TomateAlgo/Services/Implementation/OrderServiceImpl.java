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

        List<Cocktail> cocktails = event.getService().getCocktails();

        for (Cocktail cocktail : cocktails){
            for (CocktailIngredients ci : cocktail.getIngredients()){
                Product product = ci.getProduct();
                double ouncesPerCocktail = ci.getOunces();

                double totalOnces = guests * estimatedDrinkPerPerson * ouncesPerCocktail;
                productTotals.merge(product,totalOnces,Double::sum);
            }
        }

        Map<ProductType , Double> totalsByProductType = new EnumMap<>(ProductType.class);
        Map<AlcoholType, Double> totalsByAlcoholType = new EnumMap<>(AlcoholType.class);

        for (Map.Entry<Product,Double> e: productTotals.entrySet()){
            Product p = e.getKey();
            double amount = e.getValue();

            totalsByProductType.merge(p.getType(),amount, Double::sum);
            if (p.getType() == ProductType.Insumo_Alcoholico && p.getAlcoholType() != null){
                totalsByAlcoholType.merge(p.getAlcoholType(), amount, Double::sum);
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

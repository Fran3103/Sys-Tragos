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


        Optional<Order> opt = orderRepository.findByEventId(event.getId());
        Order order = opt.orElseGet(() -> {
            Order o = new Order();
            o.setEvent(event);
            return o;
        });
        order.setGeneratedAt(LocalDate.now());

        //Relaciones ServiceCocktail
        List<ServiceCocktail> scList = event.getService().getCocktails();

        //Acumulador de onzas por producto
        Map<Product, Double> productTotals  = new HashMap<>();

        if (!scList.isEmpty()) {
            int guests = event.getInvitaCant();
            int drinkPerPerson = 4;
            int totalDrinks = guests * drinkPerPerson;
            int sumIncidences = scList.stream().mapToInt(ServiceCocktail::getIncidence).sum();

            // se distribuyen los tragos segun incidencias
            for (ServiceCocktail sc : scList) {

                double portion = totalDrinks * (sc.getIncidence() / (double) sumIncidences);
                int count = (int) Math.round(portion);

                // suma ingredientes del coctel
                for (CocktailIngredients ci : sc.getCocktail().getIngredients()) {

                    double ouncesNeeded = count * ci.getOunces();
                    productTotals.merge(ci.getProduct(), ouncesNeeded, Double::sum);
                }
            }
        }


        List<OrderItem> items = productTotals.entrySet().stream()
                .map(e -> {
                   Product product = e.getKey();
                   double ounces = e.getValue();
                   int units;

                   if (product.getProductType() == ProductType.Fruta){
                       double gramsNeeded = ounces * 28.34;
                       units = (int) Math.ceil((gramsNeeded / product.getCapacity()));
                   }
                   else {
                       double mlNeed = ounces * 29.5735;
                       units = (int) Math.ceil(mlNeed / product.getCapacity());
                   }
                   return new OrderItem(product,ounces,units,order);
                })
                .collect(Collectors.toList());
        order.setItems(items);
        return orderRepository.save(order);

    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAllWithEvent();
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public String delete(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada con id: " + id));

        orderRepository.delete(order);
        return "Orden eliminada correctamente (id: " + id + ")";
    }
}

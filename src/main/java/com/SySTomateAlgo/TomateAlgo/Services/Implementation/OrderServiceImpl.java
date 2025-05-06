package com.SySTomateAlgo.TomateAlgo.Services.Implementation;

import com.SySTomateAlgo.TomateAlgo.Entities.*;
import com.SySTomateAlgo.TomateAlgo.Repositories.OrderRepository;
import com.SySTomateAlgo.TomateAlgo.Repositories.ProductRepository;
import com.SySTomateAlgo.TomateAlgo.Services.CoefService;
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
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CoefService coefService;

    @Override
    public Order generateOrderFromEvent(Event event) {


        Optional<Order> opt = orderRepository.findByEventId(event.getId());
        Order order = opt.orElseGet(() -> {
            Order o = new Order();
            o.setEvent(event);
            return o;
        });
        order.setGeneratedAt(LocalDate.now());

        double drinksPerPersonPerHour = coefService.getDrinksPerPersonsPerHour();              // coef. tragos x persona x hora
        double avgOuncesPerDrink      = coefService.getAvgOuncesPerDrink();                   // oz trago promedio
        double eventTypeCoef          = coefService.getEventTypeCoef(event.getTypeEvent());        // coef. tipo de evento
        double climateCoef            = coefService.getCLimateCoef(event.getClimateType());       // coef. clima
        double ageCoef                = coefService.getAgeCoef(event.getAgeInvita());        // coef. edad
        double durationHours          = event.getDuration();                             // duración del evento en horas
        int    pax                    = event.getInvitaCant();



        double consumptionPerPersonOz = drinksPerPersonPerHour
                * avgOuncesPerDrink
                *eventTypeCoef
                *climateCoef
                *ageCoef
                *durationHours;

        double totalConsumptionOz = consumptionPerPersonOz * pax;


        List<ServiceCocktail> scList = event.getService().getCocktails();
        Map<Product, Double> productTotalsOz = new HashMap<>();

        if (!scList.isEmpty()) {
            // suma de incidencias de cocktails
            int sumIncidences = scList.stream()
                    .mapToInt(ServiceCocktail::getIncidence)
                    .sum();


            for (ServiceCocktail sc : scList) {
                double cocktailFraction       = sc.getIncidence() / (double) sumIncidences;
                double consumptionByCocktail  = cocktailFraction * totalConsumptionOz;

                // porcentaje de cada ingrediente dentro del cocktail
                double totalRecipeOz = sc.getCocktail().getIngredients().stream()
                        .mapToDouble(CocktailIngredients::getOunces)
                        .sum();

                for (CocktailIngredients ci : sc.getCocktail().getIngredients()) {
                    double ingredientFraction = ci.getOunces() / totalRecipeOz;
                    double ingredientOz       = consumptionByCocktail * ingredientFraction;
                    productTotalsOz.merge(ci.getProduct(), ingredientOz, Double::sum);
                }
            }
        }

        // 5️⃣ Calcular unidades a pedir por producto
        List<OrderItem> items = productTotalsOz.entrySet().stream()
                .map(e -> {
                    Product product = e.getKey();
                    double   oz      = e.getValue();
                    int      units;

                    if (product.getProductType() == ProductType.Fruta) {
                        // 1 Oz ≈ 28.34 gramos
                        double grams      = oz * 28.34;
                        units = (int) Math.ceil(grams / product.getCapacity());
                    } else {
                        // 1 Oz ≈ 29.5735 ml
                        double mlNeeded   = oz * 29.5735;
                        units = (int) Math.ceil(mlNeeded / product.getCapacity());
                    }

                    return new OrderItem(product, oz, units, order);
                })
                .collect(Collectors.toList());

        double sumItemsOz = items.stream()
                .mapToDouble(OrderItem::getOunces)
                .sum();


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

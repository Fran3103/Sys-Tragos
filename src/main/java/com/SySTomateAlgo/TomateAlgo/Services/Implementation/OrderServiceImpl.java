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


        if (order.getItems() == null) {
            order.setItems(new ArrayList<>());
        }


        double drinksPerPersonPerHour = coefService.getDrinksPerPersonsPerHour();              // coef. tragos x persona x hora
        double avgOuncesPerDrink      = coefService.getAvgOuncesPerDrink();                   // oz trago promedio
        double eventTypeCoef          = coefService.getEventTypeCoef(event.getTypeEvent());        // coef. tipo de evento
        double climateCoef            = coefService.getCLimateCoef(event.getClimateType());       // coef. clima
        double ageCoef                = coefService.getAgeCoef(event.getAgeInvita());        // coef. edad
        double durationHours          = event.getDuration();                             // duración del evento en horas
        int    pax                    = event.getInvitaCant();
        double totalConsumptionOz;


        if (event.getMode() == EventMode.Pack){
            double adjustedOzPerDrink = avgOuncesPerDrink
                    *eventTypeCoef
                    *climateCoef
                    *ageCoef;

            totalConsumptionOz = adjustedOzPerDrink * pax;
        }else {
            double consumptionPerPersonOz = drinksPerPersonPerHour
                    * avgOuncesPerDrink
                    *eventTypeCoef
                    *climateCoef
                    *ageCoef
                    *durationHours;

            totalConsumptionOz = consumptionPerPersonOz * pax;
        }






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

        // calcular la cristaleria

        List<Product> cristaleria = event.getService().getCristaleria();

        for (Product product : cristaleria){
            if(product.getProductType() == ProductType.Cristaleria && product.getIncidence() != null){

                int capacityPerBox = product.getCapacityPerBox() != null ? product.getCapacityPerBox() : 1;
                int totalVasos = (int) Math.ceil(product.getIncidence() * event.getInvitaCant() * 2);

                int units = (int) (Math.ceil((double) totalVasos/ capacityPerBox) * capacityPerBox);

                OrderItem item = new OrderItem(product,0.0,units,order);
                order.getItems().add(item);
            }
        }

        // calcular hielo

        List<Product> hielo = productRepository.findByProductType(ProductType.Hielo);

        if (hielo.isEmpty()){
            throw new RuntimeException("No se encontro producto tipo hielo");
        }



        double hieloBase = event.getInvitaCant() *1.5;

        // tomo el clima y la edad de la gente, mas arriba esta declarado.
        double hieloAjustado = hieloBase * climateCoef * ageCoef;

        double capacidadBolsas = hielo.get(0).getCapacity() / 1000.0;


        int bolsas = (int) Math.ceil(hieloAjustado / capacidadBolsas);

        OrderItem hieloItem = new OrderItem(hielo.get(0), 0.0, bolsas,order);
        order.getItems().add(hieloItem);


        // 5️⃣ Calcular unidades a pedir por producto
        List<OrderItem> items = new ArrayList<>(order.getItems());

        items.addAll(
                productTotalsOz.entrySet().stream()

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
                }).toList());



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

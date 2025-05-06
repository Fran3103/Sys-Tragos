package com.SySTomateAlgo.TomateAlgo.Services.Implementation;

import com.SySTomateAlgo.TomateAlgo.Entities.AgeInvita;
import com.SySTomateAlgo.TomateAlgo.Entities.ClimateType;
import com.SySTomateAlgo.TomateAlgo.Entities.EventType;
import com.SySTomateAlgo.TomateAlgo.Services.CoefService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CoefServiceImpl implements CoefService {

    @Value("${coef.drinksPerPersonPerHour:1.0}")
    private  double drinkPerPersonPerHour;

    @Value("${coef.avgOuncesPerDrink:5}")
    private double avgOuncesPerDrink;



    @Override
    public double getDrinksPerPersonsPerHour() {
        return drinkPerPersonPerHour;
    }

    @Override
    public double getAvgOuncesPerDrink() {
        return avgOuncesPerDrink;
    }

    @Override
    public double getEventTypeCoef(EventType type) {
        return switch (type){
            case Corporativo ->  1.0;
            case Cumpleaños-> 1.2;
            case Casamiento -> 1.2;
            case Cumpleaños_15 -> 1.0;
            case Exposicion -> 1.0;
            default ->1.0;
        };
    }

    @Override
    public double getCLimateCoef(ClimateType weather) {
        return switch (weather){
            case Caluroso -> 1.1;
            case Muy_Caluroso -> 1.3;
            case Templado -> 1.0;
            case Frio -> 1.0;
            default -> 1.0;
        };
    }

    @Override
    public double getAgeCoef(AgeInvita averageAge) {
       return switch (averageAge){
           case   Jovenes_18_a_25 -> 1.3 ;
           case Adultos_25_mas -> 1.2 ;
           case Mayor_a_35 -> 1.1;
           default -> 1.0;
       };
    }

    @Override
    public double getIceCoef() {
        return 0.1;
    }
}

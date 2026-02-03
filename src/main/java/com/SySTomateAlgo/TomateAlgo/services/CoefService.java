package com.SySTomateAlgo.TomateAlgo.services;

import com.SySTomateAlgo.TomateAlgo.entities.AgeInvita;
import com.SySTomateAlgo.TomateAlgo.entities.ClimateType;
import com.SySTomateAlgo.TomateAlgo.entities.EventType;

public interface CoefService {

    double getDrinksPerPersonsPerHour();
    double getAvgOuncesPerDrink();
    double getEventTypeCoef(EventType type);
    double getCLimateCoef(ClimateType weather);
    double getAgeCoef(AgeInvita averageAge);
    double getIceCoef();


}

package com.SySTomateAlgo.TomateAlgo.Services;

import com.SySTomateAlgo.TomateAlgo.Entities.AgeInvita;
import com.SySTomateAlgo.TomateAlgo.Entities.ClimateType;
import com.SySTomateAlgo.TomateAlgo.Entities.EventType;

public interface CoefService {

    double getDrinksPerPersonsPerHour();
    double getAvgOuncesPerDrink();
    double getEventTypeCoef(EventType type);
    double getCLimateCoef(ClimateType weather);
    double getAgeCoef(AgeInvita averageAge);
    double getIceCoef();


}

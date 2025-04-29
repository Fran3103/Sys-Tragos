package com.SySTomateAlgo.TomateAlgo.Services;

import com.SySTomateAlgo.TomateAlgo.Entities.ServiceCocktail;
import java.util.List;

public interface ServiceCocktailService {

    /**
     * Agrega un cóctel al servicio con su incidencia.
     */
    ServiceCocktail addToService(Long serviceId, Long cocktailId, Integer incidence);

    /**
     * Actualiza la incidencia de un cóctel ya existente en el servicio.
     */
    ServiceCocktail updateIncidence(Long serviceId, Long scId, Integer incidence);

    /**
     * Obtiene todas las relaciones ServiceCocktail de un servicio.
     */
    List<ServiceCocktail> findByService(Long serviceId);

    /**
     * Elimina una incidencia (cóctel) del servicio.
     */
    void removeFromService(Long serviceId, Long scId);
}


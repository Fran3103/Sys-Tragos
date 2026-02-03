package com.SySTomateAlgo.TomateAlgo.services;

import com.SySTomateAlgo.TomateAlgo.entities.Service;

import java.util.List;
import java.util.Optional;

public interface ServiceService {
    Service save (Service service);
    List<Service> findAll();
    Optional<Service> findById(Long id);
    void delete(Long id);
    Service update (Long id, Service service);


    Service removeCocktail(Long serviceId, Long cocktailId);
    Service saveNew(String name);
}

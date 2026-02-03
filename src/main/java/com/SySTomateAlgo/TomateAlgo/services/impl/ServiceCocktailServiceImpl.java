package com.SySTomateAlgo.TomateAlgo.services.impl;

import com.SySTomateAlgo.TomateAlgo.entities.Cocktail;
import com.SySTomateAlgo.TomateAlgo.entities.Service;
import com.SySTomateAlgo.TomateAlgo.entities.ServiceCocktail;
import com.SySTomateAlgo.TomateAlgo.repositories.CocktailRepository;
import com.SySTomateAlgo.TomateAlgo.repositories.ServiceCocktailRepository;
import com.SySTomateAlgo.TomateAlgo.repositories.ServiceRepository;
import com.SySTomateAlgo.TomateAlgo.services.ServiceCocktailService;
import jakarta.persistence.EntityNotFoundException;


import java.util.List;

@org.springframework.stereotype.Service
public class ServiceCocktailServiceImpl implements ServiceCocktailService {

    private final ServiceRepository serviceRepo;
    private final CocktailRepository cocktailRepo;
    private final ServiceCocktailRepository scRepo;

    public ServiceCocktailServiceImpl(
            ServiceRepository serviceRepo,
            CocktailRepository cocktailRepo,
            ServiceCocktailRepository scRepo) {
        this.serviceRepo = serviceRepo;
        this.cocktailRepo = cocktailRepo;
        this.scRepo = scRepo;
    }

    @Override
    public ServiceCocktail addToService(Long serviceId, Long cocktailId, Integer incidence) {
        Service service = serviceRepo.findById(serviceId)
                .orElseThrow(() -> new EntityNotFoundException("Service no encontrado"));
        Cocktail cocktail = cocktailRepo.findById(cocktailId)
                .orElseThrow(() -> new EntityNotFoundException("Cocktail no encontrado"));

        ServiceCocktail sc = new ServiceCocktail();
        sc.setService(service);
        sc.setCocktail(cocktail);
        sc.setIncidence(incidence);

        service.addServiceCocktail(sc);
        return scRepo.save(sc);
    }

    @Override
    public ServiceCocktail updateIncidence(Long serviceId, Long scId, Integer incidence) {
        // verifica que pertenece al servicio
        ServiceCocktail sc = scRepo.findById(scId)
                .orElseThrow(() -> new EntityNotFoundException("ServiceCocktail no encontrado"));
        if (!sc.getService().getId().equals(serviceId)) {
            throw new IllegalArgumentException("El ServiceCocktail no pertenece al Service");
        }
        sc.setIncidence(incidence);
        return scRepo.save(sc);
    }

    @Override
    public List<ServiceCocktail> findByService(Long serviceId) {
        return scRepo.findByServiceId(serviceId);
    }

    @Override
    public void removeFromService(Long serviceId, Long scId) {
        ServiceCocktail sc = scRepo.findById(scId)
                .orElseThrow(() -> new EntityNotFoundException("ServiceCocktail no encontrado"));
        if (!sc.getService().getId().equals(serviceId)) {
            throw new IllegalArgumentException("El ServiceCocktail no pertenece al Service");
        }
        sc.getService().removeServiceCocktail(sc);
        scRepo.delete(sc);
    }
}

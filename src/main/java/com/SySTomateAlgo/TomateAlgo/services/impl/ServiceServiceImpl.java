package com.SySTomateAlgo.TomateAlgo.services.impl;


import com.SySTomateAlgo.TomateAlgo.entities.Event;
import com.SySTomateAlgo.TomateAlgo.entities.Service;
import com.SySTomateAlgo.TomateAlgo.repositories.CocktailRepository;
import com.SySTomateAlgo.TomateAlgo.repositories.EventRepository;
import com.SySTomateAlgo.TomateAlgo.repositories.ServiceCocktailRepository;
import com.SySTomateAlgo.TomateAlgo.repositories.ServiceRepository;
import com.SySTomateAlgo.TomateAlgo.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;



@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {
    @Autowired
    public ServiceRepository repository;

    @Autowired
    public CocktailRepository cocktailRepository;

    @Autowired
    public ServiceCocktailRepository serviceCocktailRepository;

    @Autowired
    public  EventRepository eventRepository;


    public ServiceServiceImpl(ServiceCocktailRepository serviceCocktailRepository, CocktailRepository cocktailRepository, ServiceRepository repository) {
        this.serviceCocktailRepository = serviceCocktailRepository;
        this.cocktailRepository = cocktailRepository;
        this.repository = repository;
    }

    @Override
    public Service save(Service servicio) {

        return repository.save(servicio);
    }

    @Override
    public List<Service> findAll() {
        return repository.findAllWithCocktails();


    }

    @Override
    public Optional<Service> findById(Long id) {
        return repository.findByIdWithCocktails(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        List<Event> events = eventRepository.findAllByServiceId(id);
        for (Event e : events) {
            e.setService(null);
            eventRepository.save(e);
        }

        repository.deleteById(id);


    }

    @Override
    public Service update(Long id, Service serviceData) {
        Service existService = repository.findById(id)
                .orElseThrow(()-> new RuntimeException("Servicio no encontrado"));
        if (serviceData.getName() !=null) {
            existService.setName(serviceData.getName());
        };

        existService.setCristaleria(serviceData.getCristaleria());

        return repository.save(existService);
    }



    @Override
    public Service removeCocktail(Long serviceId, Long cocktailId) {
        throw new UnsupportedOperationException(
                "Use ServiceCocktailController → ServiceCocktailService para eliminar incidencias");
    }

    @Override
    public Service saveNew(String name) {
        Service svc = new Service();
        svc.setName(name);
        return repository.save(svc);
    }
}

package com.SySTomateAlgo.TomateAlgo.Services.Implementation;

import com.SySTomateAlgo.TomateAlgo.Entities.Cocktail;
import com.SySTomateAlgo.TomateAlgo.Entities.Service;
import com.SySTomateAlgo.TomateAlgo.Repositories.CocktailRepository;
import com.SySTomateAlgo.TomateAlgo.Repositories.ServiceCocktailRepository;
import com.SySTomateAlgo.TomateAlgo.Repositories.ServiceRepository;
import com.SySTomateAlgo.TomateAlgo.Services.ServiceCocktailService;
import com.SySTomateAlgo.TomateAlgo.Services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;

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
        return repository.findAll();
    }

    @Override
    public Optional<Service> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);

    }

    @Override
    public Service update(Long id, Service serviceData) {
        Service existService = repository.findById(id)
                .orElseThrow(()-> new RuntimeException("Servicio no encontrado"));
        if (serviceData.getName() !=null) {
            existService.setName(serviceData.getName());
        };

        return repository.save(existService);
    }

    @Override
    public Service addCocktail(Long serviceId, Long cocktailId) {
        throw new UnsupportedOperationException(
                "Use ServiceCocktailController → ServiceCocktailService para añadir incidencias");
    }

    @Override
    public Service removeCocktail(Long serviceId, Long cocktailId) {
        throw new UnsupportedOperationException(
                "Use ServiceCocktailController → ServiceCocktailService para eliminar incidencias");
    }
}

package com.SySTomateAlgo.TomateAlgo.Services.Implementation;

import com.SySTomateAlgo.TomateAlgo.Entities.Cocktail;
import com.SySTomateAlgo.TomateAlgo.Entities.Service;
import com.SySTomateAlgo.TomateAlgo.Repositories.CocktailRepository;
import com.SySTomateAlgo.TomateAlgo.Repositories.ServiceRepository;
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

    @Override
    public Service save(Service servicio) {

        if (servicio.getCocktails() != null && !servicio.getCocktails().isEmpty()){
            List<Long> ids = servicio.getCocktails().stream()
                    .map(c-> c.getId())
                    .toList();

            List<Cocktail> fullCocktails = cocktailRepository.findAllById(ids);
            servicio.setCocktails(fullCocktails);
        }

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

        if (serviceData.getCocktails() != null){
                List<Long> Ids = serviceData.getCocktails().stream()
                        .map(c-> c.getId())
                        .toList();
                List<Cocktail> fullCocktails = cocktailRepository.findAllById(Ids);
                existService.setCocktails(fullCocktails);}

        return repository.save(existService);
    }

    @Override
    public Service addCocktail(Long serviceId, Long cocktailId) {
        Service service = repository.findById(serviceId)
                .orElseThrow(()-> new RuntimeException("servicio no encontrado"));

        Cocktail cocktail = cocktailRepository.findById(cocktailId)
                .orElseThrow(()-> new RuntimeException("Cocktail no encontrado"));


        List<Cocktail> cocktails = service.getCocktails();

        if (!cocktails.contains(cocktail)){
            cocktails.add(cocktail);
            service.setCocktails(cocktails);
        }

        return repository.save(service);
    }

    @Override
    public Service removeCocktail(Long serviceId, Long cocktailId) {
        Service service = repository.findById(serviceId)
                .orElseThrow(()-> new RuntimeException("servicio no encontrado"));

        Cocktail cocktail  = cocktailRepository.findById(cocktailId)
                .orElseThrow(()-> new RuntimeException("Cocktial no encontrado"));

        List<Cocktail> cocktails = service.getCocktails();

        cocktails.removeIf(c-> c.getId().equals(cocktailId));;

        service.setCocktails(cocktails);

        return repository.save(service);
    }
}

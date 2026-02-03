package com.SySTomateAlgo.TomateAlgo.services.impl;

import com.SySTomateAlgo.TomateAlgo.dtos.CocktailIngredientDTO;
import com.SySTomateAlgo.TomateAlgo.dtos.CocktailRequestDTO;
import com.SySTomateAlgo.TomateAlgo.entities.Cocktail;
import com.SySTomateAlgo.TomateAlgo.entities.CocktailIngredients;
import com.SySTomateAlgo.TomateAlgo.entities.Product;
import com.SySTomateAlgo.TomateAlgo.repositories.CocktailRepository;
import com.SySTomateAlgo.TomateAlgo.repositories.ProductRepository;
import com.SySTomateAlgo.TomateAlgo.services.CocktailService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CocktailServiceImpl implements CocktailService {

    @Autowired
    public CocktailRepository repository;
    @Autowired
    public ProductRepository productRepository;


    @Override
    public Cocktail save(CocktailRequestDTO dto) {

        Cocktail cocktail = new Cocktail();
        cocktail.setName(dto.getName());
        cocktail.setDescription(dto.getDescription());

        for (CocktailIngredientDTO ing : dto.getIngredients()){
            Product p = productRepository.findById(ing.getProductId())
                    .orElseThrow(()-> new EntityNotFoundException("Producto no encontrado"));
            CocktailIngredients ci = new CocktailIngredients(ing.getOunces(),p,cocktail);
            cocktail.addIngredient(ci);
        }

        return repository.save(cocktail);
    }

    @Override
    public List<Cocktail> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Cocktail> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void delete(Long id) {
            repository.deleteById(id);
    }

    @Override
    public void  removeIngredient(Long cocktailId, Long productID){
        Cocktail cocktail = repository.findById(cocktailId)
                .orElseThrow(()-> new RuntimeException("Cocktail no encontrado"));

        Optional<CocktailIngredients> ciOpt = cocktail.getIngredients().stream().filter(ci -> ci.getProduct().getId().equals(productID)).findFirst();

        if (ciOpt.isEmpty()){
            throw  new EntityNotFoundException("Ingrediente no encontrado");
        }

        CocktailIngredients ci = ciOpt.get();
        cocktail.removeIngredient(ci);

        repository.save(cocktail);
    }

    @Override
    public Cocktail update(Long id, CocktailRequestDTO dto) {
        Cocktail exist = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cocktail no encontrado"));

        // 1. Actualiza sólo nombre y descripción
        if (dto.getName() != null) {
            exist.setName(dto.getName());
        }
        if (dto.getDescription() != null) {
            exist.setDescription(dto.getDescription());
        }

        if (dto.getIngredients() != null) {
            // 2. Prepara un Map de los ingredientes actuales por productId
            Map<Long, CocktailIngredients> actuales = exist.getIngredients().stream()
                    .collect(Collectors.toMap(
                            ci -> ci.getProduct().getId(),
                            ci -> ci
                    ));

        // 3. Recorre los del DTO: añade nuevos o actualiza onzas
        Set<Long> dtoIds = new HashSet<>();
        for (CocktailIngredientDTO ingDto : dto.getIngredients()) {
            Long prodId = ingDto.getProductId();
            dtoIds.add(prodId);

            CocktailIngredients ciExist = actuales.get(prodId);
            if (ciExist != null) {
                // Ya estaba: actualiza sólo las onzas
                ciExist.setOunces(ingDto.getOunces());
            } else {
                // No existía: lo creas y añades
                Product p = productRepository.findById(prodId)
                        .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));
                CocktailIngredients ciNew = new CocktailIngredients(ingDto.getOunces(), p, exist);
                exist.addIngredient(ciNew);
            }
        }
        }

        /*4. Elimina los ingredientes que antes había y ahora no vienen en el DTO
        List<CocktailIngredients> toRemove = exist.getIngredients().stream()
                .filter(ci -> !dtoIds.contains(ci.getProduct().getId()))
                .collect(Collectors.toList());
        for (CocktailIngredients ci : toRemove) {
            exist.removeIngredient(ci);
        }
        */


        // 5. Guarda y retorna
        return repository.save(exist);
    }
}

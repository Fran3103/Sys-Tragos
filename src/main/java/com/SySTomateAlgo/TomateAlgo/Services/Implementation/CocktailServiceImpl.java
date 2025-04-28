package com.SySTomateAlgo.TomateAlgo.Services.Implementation;

import com.SySTomateAlgo.TomateAlgo.DTOs.CocktailIngredientDTO;
import com.SySTomateAlgo.TomateAlgo.DTOs.CocktailRequestDTO;
import com.SySTomateAlgo.TomateAlgo.Entities.Cocktail;
import com.SySTomateAlgo.TomateAlgo.Entities.CocktailIngredients;
import com.SySTomateAlgo.TomateAlgo.Entities.Product;
import com.SySTomateAlgo.TomateAlgo.Repositories.CocktailRepository;
import com.SySTomateAlgo.TomateAlgo.Repositories.ProductRepository;
import com.SySTomateAlgo.TomateAlgo.Services.CocktailService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public Cocktail update(Long id, CocktailRequestDTO dto) {
        Cocktail existCocktail = repository.findById(id)
                .orElseThrow(()-> new RuntimeException("Cocktail no encontrado"));
        existCocktail.setName(dto.getName());
        existCocktail.setDescription(dto.getDescription());

        existCocktail.getIngredients().clear();

        for (CocktailIngredientDTO ing : dto.getIngredients()){
            Product p = productRepository.findById(ing.getProductId())
                    .orElseThrow(()-> new EntityNotFoundException("Producto no encontrado"));
            CocktailIngredients ci = new CocktailIngredients(ing.getOunces(), p,existCocktail);
            existCocktail.addIngredient(ci);
        }

        return repository.save(existCocktail);

    }
}

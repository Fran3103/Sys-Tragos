package com.SySTomateAlgo.TomateAlgo.Services.Implementation;

import com.SySTomateAlgo.TomateAlgo.Entities.Cocktail;
import com.SySTomateAlgo.TomateAlgo.Entities.Product;
import com.SySTomateAlgo.TomateAlgo.Repositories.CocktailRepository;
import com.SySTomateAlgo.TomateAlgo.Repositories.ProductRepository;
import com.SySTomateAlgo.TomateAlgo.Services.CocktailService;
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
    public Cocktail save(Cocktail cocktail) {

        List<Long> ingredientId = cocktail.getIngredients()
                .stream()
                .map(Product::getId)
                .toList();

        List<Product> fullIngredients = productRepository.findAllById(ingredientId);

        cocktail.setIngredients(fullIngredients);
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
    public Cocktail update(Long id, Cocktail cocktailData) {
        Cocktail existCocktail = repository.findById(id)
                .orElseThrow(()-> new RuntimeException("Cocktail no encontrado"));
        existCocktail.setName(cocktailData.getName());
        existCocktail.setDescription(cocktailData.getDescription());

        List<Long> newIds = cocktailData.getIngredients()
                .stream()
                .map(Product::getId)
                .toList();

        List<Product> newIngredients = productRepository.findAllById(newIds);

        List<Product> allIngredients = new ArrayList<>(existCocktail.getIngredients());

        allIngredients.addAll(newIngredients);

        Set<Product> uniqueIngredients = new HashSet<>(allIngredients);



        existCocktail.setIngredients(new ArrayList<>(uniqueIngredients));

        return repository.save(existCocktail);
    }
}

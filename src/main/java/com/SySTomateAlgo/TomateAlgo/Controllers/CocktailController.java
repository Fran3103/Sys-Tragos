package com.SySTomateAlgo.TomateAlgo.Controllers;

import com.SySTomateAlgo.TomateAlgo.Entities.Cocktail;
import com.SySTomateAlgo.TomateAlgo.Entities.Product;
import com.SySTomateAlgo.TomateAlgo.Repositories.CocktailRepository;
import com.SySTomateAlgo.TomateAlgo.Repositories.ProductRepository;
import com.SySTomateAlgo.TomateAlgo.Services.CocktailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cocktails")
public class CocktailController {

    @Autowired
    private CocktailService service;
    @Autowired
    private CocktailRepository repository;
    @Autowired
    private ProductRepository productRepository;


    @GetMapping
    public ResponseEntity<List<Cocktail>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }


    @PostMapping
    public ResponseEntity<Cocktail> save(@RequestBody Cocktail Cocktail){
        return ResponseEntity.ok(service.save(Cocktail));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cocktail> findById(@PathVariable Long id){
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cocktail> update(@PathVariable Long id, @RequestBody Cocktail Cocktail){
        return ResponseEntity.ok(service.update(id, Cocktail));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.ok("El Cocktail fue eliminado con exito");
    }

    @DeleteMapping("/{cocktailId}/ingredient/{ingredientId}")
    public ResponseEntity<String> removeIngredient(
            @PathVariable Long cocktailId,
            @PathVariable Long ingredientId
    ){
        Cocktail cocktail = repository.findById(cocktailId)
                .orElseThrow(()-> new RuntimeException("Cocktail no encontrado"));

        List<Product> ingredients = cocktail.getIngredients();

        ingredients.removeIf(product -> product.getId().equals(ingredientId));

        cocktail.setIngredients(ingredients);

        repository.save(cocktail);

        return ResponseEntity.ok("Ingrediente Eliminado con exito");
    }


    @PostMapping("/{cocktailId}/ingredient/{ingredientId}")
    public ResponseEntity<String> addIngredientToCocktail(
            @PathVariable Long cocktailId,
            @PathVariable Long ingredientId
    ){
        Cocktail cocktail  = service.findById(cocktailId)
                .orElseThrow(()-> new RuntimeException("Cocktail no encontrado"));

        Product product = productRepository.findById(ingredientId)
                .orElseThrow(()-> new RuntimeException("Ingrediente  no encontrado"));

        List<Product> ingredients = cocktail.getIngredients();

        if (!ingredients.contains(product)){
            ingredients.add(product);
            cocktail.setIngredients(ingredients);
            repository.save(cocktail);
        }

        return ResponseEntity.ok("Ingrediente agregado");
    }
}

package com.SySTomateAlgo.TomateAlgo.Controllers;

import com.SySTomateAlgo.TomateAlgo.DTOs.CocktailIngredientDTO;
import com.SySTomateAlgo.TomateAlgo.DTOs.CocktailRequestDTO;
import com.SySTomateAlgo.TomateAlgo.Entities.Cocktail;
import com.SySTomateAlgo.TomateAlgo.Entities.CocktailIngredients;
import com.SySTomateAlgo.TomateAlgo.Entities.Product;
import com.SySTomateAlgo.TomateAlgo.Repositories.CocktailRepository;
import com.SySTomateAlgo.TomateAlgo.Repositories.ProductRepository;
import com.SySTomateAlgo.TomateAlgo.Services.CocktailService;
import com.SySTomateAlgo.TomateAlgo.Utils.UpdatePropertiesUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cocktails")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
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
    public ResponseEntity<Cocktail> save(@RequestBody CocktailRequestDTO dto){
        return ResponseEntity.ok(service.save(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        if(service.findById(id).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cocktail no encontrado");
        }
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody CocktailRequestDTO dto){
        if (service.findById(id).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cocktail no encontrado");
        }
        return ResponseEntity.ok(service.update(id, dto));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        if (service.findById(id).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cocktail no encontrado");
        }   else service.delete(id);
        return ResponseEntity.ok("El Cocktail fue eliminado con exito");
    }

    @DeleteMapping("/{cocktailId}/ingredients/{productId}")
    public ResponseEntity<?> deleteIngredient(
            @PathVariable Long cocktailId,
            @PathVariable Long productId
    ) {
        if (service.findById(cocktailId).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cocktail no encontrado");
        }
        if (service.findById(productId).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ingrediente no encontrado");
        }else
            service.removeIngredient(cocktailId, productId);

            return ResponseEntity.ok("El Cocktail fue eliminado con exito");
    }


    @PostMapping("/{cocktailId}/ingredient")
    public ResponseEntity<String> addIngredient(
            @PathVariable Long cocktailId,
            @RequestBody CocktailIngredientDTO dto
            ){
        Cocktail cocktail  = service.findById(cocktailId)
                .orElseThrow(()-> new RuntimeException("Cocktail no encontrado"));

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(()-> new RuntimeException("Ingrediente  no encontrado"));


        CocktailIngredients ci = new CocktailIngredients(dto.getOunces(), product, cocktail);
        cocktail.addIngredient(ci);

        repository.save(cocktail);

        return ResponseEntity.ok("Ingrediente agregado");
    }
}

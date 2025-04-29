package com.SySTomateAlgo.TomateAlgo.Services;

import com.SySTomateAlgo.TomateAlgo.DTOs.CocktailRequestDTO;
import com.SySTomateAlgo.TomateAlgo.Entities.Cocktail;


import java.util.List;
import java.util.Optional;

public interface CocktailService {

    Cocktail save (CocktailRequestDTO dto);
    List<Cocktail> findAll();
    Optional<Cocktail> findById(Long id);
    void delete(Long id);
    Cocktail update (Long id, CocktailRequestDTO dto);

    void removeIngredient(Long cocktailId, Long productId);

}

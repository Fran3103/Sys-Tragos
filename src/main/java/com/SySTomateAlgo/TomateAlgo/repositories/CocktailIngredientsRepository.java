package com.SySTomateAlgo.TomateAlgo.repositories;

import com.SySTomateAlgo.TomateAlgo.entities.CocktailIngredients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CocktailIngredientsRepository extends JpaRepository<CocktailIngredients, Long> {
    void deleteByProductId(Long productID);
}

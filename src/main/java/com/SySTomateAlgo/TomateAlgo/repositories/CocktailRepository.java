package com.SySTomateAlgo.TomateAlgo.repositories;

import com.SySTomateAlgo.TomateAlgo.entities.Cocktail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CocktailRepository extends JpaRepository<Cocktail, Long> {
}

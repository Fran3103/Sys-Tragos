package com.SySTomateAlgo.TomateAlgo.Repositories;

import com.SySTomateAlgo.TomateAlgo.Entities.Cocktail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CocktailRepository extends JpaRepository<Cocktail, Long> {
}

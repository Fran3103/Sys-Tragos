package com.SySTomateAlgo.TomateAlgo.repositories;

import com.SySTomateAlgo.TomateAlgo.entities.ServiceCocktail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceCocktailRepository extends JpaRepository<ServiceCocktail, Long> {
    List<ServiceCocktail> findByServiceId(Long serviceId);
}

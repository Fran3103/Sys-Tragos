package com.SySTomateAlgo.TomateAlgo.Repositories;

import com.SySTomateAlgo.TomateAlgo.Entities.ServiceCocktail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceCocktailRepository extends JpaRepository<ServiceCocktail, Long> {
    List<ServiceCocktail> findByServiceId(Long serviceId);
}

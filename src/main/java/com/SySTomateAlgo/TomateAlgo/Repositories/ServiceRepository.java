package com.SySTomateAlgo.TomateAlgo.Repositories;

import com.SySTomateAlgo.TomateAlgo.Entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceRepository extends JpaRepository <Service, Long> {
    // Para listar todos con sus cocktails
    @Query("""
     SELECT DISTINCT s
     FROM Service s
     LEFT JOIN FETCH s.cocktails sc
  """)
    List<Service> findAllWithCocktails();

    // Para buscar uno solo con sus cocktails
    @Query("""
     SELECT s
     FROM Service s
     LEFT JOIN FETCH s.cocktails sc
     WHERE s.id = :id
  """)
    Optional<Service> findByIdWithCocktails(@Param("id") Long id);
}

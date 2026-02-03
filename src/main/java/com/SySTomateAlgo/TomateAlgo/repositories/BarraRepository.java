package com.SySTomateAlgo.TomateAlgo.repositories;

import com.SySTomateAlgo.TomateAlgo.entities.Barra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BarraRepository extends JpaRepository<Barra, Long> {
    List<Barra> findByEventId(Long eventId);
}

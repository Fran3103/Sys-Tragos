package com.SySTomateAlgo.TomateAlgo.Services;

import com.SySTomateAlgo.TomateAlgo.Entities.Barra;


import java.util.List;
import java.util.Optional;

public interface BarraService {
    Barra save (Barra barra);
    List<Barra> findAll();
    Optional<Barra> findById(Long id);
    void delete(Long id);
    Barra update (Long id, Barra barra);

}

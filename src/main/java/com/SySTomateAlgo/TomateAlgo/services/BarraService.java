package com.SySTomateAlgo.TomateAlgo.services;

import com.SySTomateAlgo.TomateAlgo.dtos.BarraRequestDTO;
import com.SySTomateAlgo.TomateAlgo.dtos.BarraResponseDTO;

import java.util.List;

public interface BarraService {

    BarraResponseDTO create(BarraRequestDTO dto);
    BarraResponseDTO update(Long id, BarraRequestDTO dto);
    BarraResponseDTO findById(Long id);
    List<BarraResponseDTO> findByEventId(Long eventId);
    void delete (Long id);
    List<BarraResponseDTO> findAll();
}

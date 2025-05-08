package com.SySTomateAlgo.TomateAlgo.Services;

import com.SySTomateAlgo.TomateAlgo.DTOs.BarraRequestDTO;
import com.SySTomateAlgo.TomateAlgo.DTOs.BarraResponseDTO;

import java.util.List;

public interface BarraService {

    BarraResponseDTO create(BarraRequestDTO dto);
    BarraResponseDTO update(Long id, BarraRequestDTO dto);
    BarraResponseDTO findById(Long id);
    List<BarraResponseDTO> findByEventId(Long eventId);
    void delete (Long id);
    List<BarraResponseDTO> findAll();
}

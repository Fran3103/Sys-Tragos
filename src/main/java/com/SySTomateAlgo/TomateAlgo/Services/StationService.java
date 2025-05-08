package com.SySTomateAlgo.TomateAlgo.Services;

import com.SySTomateAlgo.TomateAlgo.DTOs.StationRequestDTO;
import com.SySTomateAlgo.TomateAlgo.DTOs.StationResponseDTO;
import com.SySTomateAlgo.TomateAlgo.Entities.Station;

import java.util.List;
import java.util.Optional;

public interface StationService {

    StationResponseDTO create(StationRequestDTO dto);
    StationResponseDTO update(Long id, StationRequestDTO dto);
    StationResponseDTO findById(Long id);
    List<StationResponseDTO> findByEventId(Long eventId);
    void delete (Long id);
    List<StationResponseDTO> findAll();
}

package com.SySTomateAlgo.TomateAlgo.Services.Implementation;

import com.SySTomateAlgo.TomateAlgo.DTOs.StationRequestDTO;
import com.SySTomateAlgo.TomateAlgo.DTOs.StationResponseDTO;
import com.SySTomateAlgo.TomateAlgo.Entities.*;
import com.SySTomateAlgo.TomateAlgo.Mapper.StationMapper;
import com.SySTomateAlgo.TomateAlgo.Repositories.BarraRepository;
import com.SySTomateAlgo.TomateAlgo.Repositories.EventRepository;
import com.SySTomateAlgo.TomateAlgo.Repositories.ProductRepository;
import com.SySTomateAlgo.TomateAlgo.Repositories.StationRepository;
import com.SySTomateAlgo.TomateAlgo.Services.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StationServiceImpl implements StationService {

    @Autowired
    public StationRepository stationRepository;

    @Autowired
    public EventRepository eventRepository;

    @Autowired
    public StationMapper stationMapper;

    @Autowired
    public ProductRepository productRepository;

    @Override
    public StationResponseDTO create(StationRequestDTO dto) {


        Station station = new Station();
        station.setName(dto.getName());

        List<StationEquipment> equipments = dto.getEquipments().stream()
                .map(equipDto -> {
                    Product product = productRepository.findById(equipDto.getProductId())
                            .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + equipDto.getProductId()));
                    StationEquipment se = new StationEquipment();
                    se.setProduct(product);
                    se.setQuantity(equipDto.getQuantity());
                    se.setStation(station);
                    return se;
                })
                .toList();

        station.setEquipments(equipments);

        Station saved = stationRepository.save(station);
        return stationMapper.toDTO(saved);
    }

    @Override
    public StationResponseDTO update(Long id, StationRequestDTO dto) {
        Station station = stationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estación no encontrada"));

        station.setName(dto.getName());


        station.getEquipments().clear();


        List<StationEquipment> newEquipments = dto.getEquipments().stream()
                .map(equipDto -> {
                    Product product = productRepository.findById(equipDto.getProductId())
                            .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + equipDto.getProductId()));
                    StationEquipment se = new StationEquipment();
                    se.setProduct(product);
                    se.setQuantity(equipDto.getQuantity());
                    se.setStation(station);
                    return se;
                })
                .toList();

        station.getEquipments().addAll(newEquipments);

        Station updated = stationRepository.save(station);
        return stationMapper.toDTO(updated);
    }

    @Override
    public StationResponseDTO findById(Long id) {
       Station station = stationRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Estacion no encontrada"));

       return stationMapper.toDTO(station);
    }

    @Override
    public List<StationResponseDTO> findByEventId(Long eventId) {
        List <Station> stations = stationRepository.findByEventId(eventId);

        return stations.stream().map(stationMapper::toDTO).toList();
    }

    @Override
    public void delete(Long id) {
        if (!stationRepository.existsById(id)) {
            throw new RuntimeException("Estación no encontrada con ID: " + id);
        }
        stationRepository.deleteById(id);
    }

    @Override
    public List<StationResponseDTO> findAll() {
        List<Station> stations = stationRepository.findAll();
        return stations.stream()
                .map(stationMapper::toDTO)
                .toList();
    }
}

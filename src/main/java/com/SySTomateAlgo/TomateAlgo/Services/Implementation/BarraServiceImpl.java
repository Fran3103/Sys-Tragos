package com.SySTomateAlgo.TomateAlgo.Services.Implementation;

import com.SySTomateAlgo.TomateAlgo.DTOs.BarraRequestDTO;
import com.SySTomateAlgo.TomateAlgo.DTOs.BarraResponseDTO;
import com.SySTomateAlgo.TomateAlgo.Entities.*;
import com.SySTomateAlgo.TomateAlgo.Mapper.BarraMapper;
import com.SySTomateAlgo.TomateAlgo.Repositories.EventRepository;
import com.SySTomateAlgo.TomateAlgo.Repositories.ProductRepository;
import com.SySTomateAlgo.TomateAlgo.Repositories.BarraRepository;
import com.SySTomateAlgo.TomateAlgo.Services.BarraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BarraServiceImpl implements BarraService {

    @Autowired
    public BarraRepository barraRepository;

    @Autowired
    public EventRepository eventRepository;

    @Autowired
    public BarraMapper barraMapper;

    @Autowired
    public ProductRepository productRepository;

    @Override
    public BarraResponseDTO create(BarraRequestDTO dto) {


        Barra barra = new Barra();
        barra.setName(dto.getName());

        List<BarraItem> equipments = dto.getEquipments().stream()
                .map(equipDto -> {
                    Product product = productRepository.findById(equipDto.getProductId())
                            .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + equipDto.getProductId()));
                    BarraItem se = new BarraItem();
                    se.setProduct(product);
                    se.setQuantity(equipDto.getQuantity());
                    se.setBarras(barra);
                    return se;
                })
                .toList();

        barra.setEquipments(equipments);

        Barra saved = barraRepository.save(barra);
        return barraMapper.toDTO(saved);
    }

    @Override
    public BarraResponseDTO update(Long id, BarraRequestDTO dto) {
        Barra barra = barraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estación no encontrada"));

        barra.setName(dto.getName());


        barra.getEquipments().clear();


        List<BarraItem> newEquipments = dto.getEquipments().stream()
                .map(equipDto -> {
                    Product product = productRepository.findById(equipDto.getProductId())
                            .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + equipDto.getProductId()));
                    BarraItem se = new BarraItem();
                    se.setProduct(product);
                    se.setQuantity(equipDto.getQuantity());
                    se.setBarras(barra);
                    return se;
                })
                .toList();

        barra.getEquipments().addAll(newEquipments);

        Barra updated = barraRepository.save(barra);
        return barraMapper.toDTO(updated);
    }

    @Override
    public BarraResponseDTO findById(Long id) {
       Barra barra = barraRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Estacion no encontrada"));

       return barraMapper.toDTO(barra);
    }

    @Override
    public List<BarraResponseDTO> findByEventId(Long eventId) {
        List <Barra> barras = barraRepository.findByEventId(eventId);

        return barras.stream().map(barraMapper::toDTO).toList();
    }

    @Override
    public void delete(Long id) {
        if (!barraRepository.existsById(id)) {
            throw new RuntimeException("Estación no encontrada con ID: " + id);
        }
        barraRepository.deleteById(id);
    }

    @Override
    public List<BarraResponseDTO> findAll() {
        List<Barra> barras = barraRepository.findAll();
        return barras.stream()
                .map(barraMapper::toDTO)
                .toList();
    }
}

package com.SySTomateAlgo.TomateAlgo.Mapper;

import com.SySTomateAlgo.TomateAlgo.DTOs.ProductDTO;
import com.SySTomateAlgo.TomateAlgo.DTOs.StationRequestDTO;
import com.SySTomateAlgo.TomateAlgo.DTOs.StationResponseDTO;
import com.SySTomateAlgo.TomateAlgo.Entities.Product;
import com.SySTomateAlgo.TomateAlgo.Entities.Station;
import com.SySTomateAlgo.TomateAlgo.Entities.StationEquipment;
import com.SySTomateAlgo.TomateAlgo.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StationMapper {

    @Autowired
    public ProductRepository productRepository;

    // pasamos del dto a la entidad
    public Station toEntity(StationRequestDTO dto){
        Station station = new Station();
        station.setName(dto.getName());

        List<StationEquipment> equipmentList = dto.getEquipments().stream()
                .map(equipDto -> {
                    Product product = productRepository.findById(equipDto.getProductId())
                            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
                    StationEquipment se = new StationEquipment();
                    se.setProduct(product);
                    se.setQuantity(equipDto.getQuantity());
                    se.setStation(station); // importante para la relación bidireccional
                    return se;
                })
                .collect(Collectors.toList());

        station.setEquipments(equipmentList);
        return station;
    }


    // pasamos de la entidad al dto
    public StationResponseDTO toDTO(Station station){
        List<ProductDTO> equipmentDTOs = station.getEquipments().stream()
                .map(se -> {
                    Product p = se.getProduct();
                    return new ProductDTO(p.getId(), p.getName(),p.getProductType().name());
                }).toList();


        return  new StationResponseDTO(
                station.getId(),
                station.getName(),
                equipmentDTOs
        );


    }


}

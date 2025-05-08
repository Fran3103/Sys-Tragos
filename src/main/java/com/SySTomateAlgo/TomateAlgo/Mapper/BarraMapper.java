package com.SySTomateAlgo.TomateAlgo.Mapper;

import com.SySTomateAlgo.TomateAlgo.DTOs.ProductDTO;
import com.SySTomateAlgo.TomateAlgo.DTOs.BarraRequestDTO;
import com.SySTomateAlgo.TomateAlgo.DTOs.BarraResponseDTO;
import com.SySTomateAlgo.TomateAlgo.Entities.Product;
import com.SySTomateAlgo.TomateAlgo.Entities.Barra;
import com.SySTomateAlgo.TomateAlgo.Entities.BarraItem;
import com.SySTomateAlgo.TomateAlgo.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BarraMapper {

    @Autowired
    public ProductRepository productRepository;

    // pasamos del dto a la entidad
    public Barra toEntity(BarraRequestDTO dto){
        Barra barra = new Barra();
        barra.setName(dto.getName());

        List<BarraItem> equipmentList = dto.getEquipments().stream()
                .map(equipDto -> {
                    Product product = productRepository.findById(equipDto.getProductId())
                            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
                    BarraItem se = new BarraItem();
                    se.setProduct(product);
                    se.setQuantity(equipDto.getQuantity());
                    se.setBarras(barra); // importante para la relación bidireccional
                    return se;
                })
                .collect(Collectors.toList());

        barra.setEquipments(equipmentList);
        return barra;
    }


    // pasamos de la entidad al dto
    public BarraResponseDTO toDTO(Barra barra){
        List<ProductDTO> equipmentDTOs = barra.getEquipments().stream()
                .map(se -> {
                    Product p = se.getProduct();
                    return new ProductDTO(p.getId(), p.getName(),p.getProductType().name());
                }).toList();


        return  new BarraResponseDTO(
                barra.getId(),
                barra.getName(),
                equipmentDTOs
        );


    }


}
